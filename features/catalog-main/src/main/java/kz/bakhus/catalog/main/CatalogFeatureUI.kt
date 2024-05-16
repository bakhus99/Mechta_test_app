package kz.bakhus.catalog.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import kotlinx.serialization.Serializable
import kz.bakhus.catalog.data.models.CatalogItem
import kz.bakhus.core.components.AddToFavButton
import kz.bakhus.core.components.ImageCarousel
import kz.bakhus.core.components.PriceDisplay
import kz.bakhus.core.components.ProductPriceSection

@Serializable
object CatalogFeatureUI

@Composable
fun CatalogMain(onProductClick: (String, String) -> Unit) {

    CatalogMain(viewModel = hiltViewModel(), onProductClick)
}

@Composable
internal fun CatalogMain(
    viewModel: CatalogMainViewModel,
    onProductClick: (String, String) -> Unit
) {

    val moviePagingItems: LazyPagingItems<CatalogItem> = viewModel.state.collectAsLazyPagingItems()
    val favorites by viewModel.favorites.collectAsState()

    LazyColumn(modifier = Modifier.padding()) {
        items(moviePagingItems.itemCount) { index ->
            val item = moviePagingItems[index]
            item?.let {
                val isFavorite = favorites.contains(item.code)
                ItemRow(
                    item,
                    onProductClick,
                    isFavorite = isFavorite,
                    onFavoriteClick = {
                        if (isFavorite) {
                            viewModel.removeFavorite(item.code)
                        } else {
                            viewModel.addFavorite(item.code)
                        }
                    })
            }

        }
        moviePagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = moviePagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = moviePagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(modifier: Modifier, message: String, onClickRetry: () -> Unit) {

    //TODO(bakhus): add implementation for retry

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = message)
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

}

@Composable
fun PageLoader(modifier: Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

}

@Composable
fun ItemRow(
    item: CatalogItem, onProductClick: (String, String) -> Unit, isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Column(modifier = Modifier
        .padding(8.dp)
        .clickable {
            onProductClick(item.code, item.name)
        }) {
        Text(
            text = item.name, modifier = Modifier
                .padding(bottom = 8.dp)

        )
        ImageCarousel(images = item.photos)

        ProductPriceSection(prices = item.prices)

        AddToFavButton(isFavorite = isFavorite, onFavoriteClick = onFavoriteClick)
    }
}



