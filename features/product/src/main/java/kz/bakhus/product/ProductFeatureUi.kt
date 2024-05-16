package kz.bakhus.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.Serializable
import kz.bakhus.core.components.AddToFavButton
import kz.bakhus.core.components.ImageCarousel
import kz.bakhus.core.components.ProductPriceSection
import kz.bakhus.product_data.models.MainProperty
import kz.bakhus.product_data.models.Product

@Serializable
data class ProductScreen(
    val productId: String,
    val title: String
)

@Composable
fun ProductMain(productId: ProductScreen, onBackClick: () -> Unit) {
    ProductMain(viewModel = hiltViewModel(), args = productId, onBackClick = onBackClick)
}

@Composable
fun ProductMain(viewModel: ProductViewModel, args: ProductScreen, onBackClick: () -> Unit) {

    val productState by viewModel.productState.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    LaunchedEffect(ProductViewModel.NavigationCommand.Back) {
        viewModel.navigationCommands.collectLatest { command ->
            when (command) {
                is ProductViewModel.NavigationCommand.Back -> onBackClick()
            }
        }

    }

    LaunchedEffect(args.productId) {
        viewModel.fetchProduct(args.productId)
    }


    val isFavorite = favorites.contains(args.productId)

    when (productState) {
        is ProductViewModel.ProductState.Loading -> LoadingScreen()
        is ProductViewModel.ProductState.Success -> SuccessScreen(
            (productState as ProductViewModel.ProductState.Success).product,
            isFavorite = isFavorite,
            title = args.title,
            onFavoriteClick = {
                if (isFavorite) {
                    viewModel.removeFavorite(args.productId)
                } else {
                    viewModel.addFavorite(args.productId)
                }
            }
        )

        is ProductViewModel.ProductState.Error -> ErrorScreen((productState as ProductViewModel.ProductState.Error).message)
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithBackButton(
    title: String,
) {
    val navBack = hiltViewModel<ProductViewModel>()
    TopAppBar(
        modifier = Modifier.padding(top = 8.dp),
        title = {
            Text(text = title, maxLines = 2)
        },
        navigationIcon = {
            IconButton(onClick = { navBack.navigateBack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}


@Composable
fun SuccessScreen(
    product: Product,
    title: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBarWithBackButton(
                title = title,
            )
        }
    ) { paddingValues ->
        // Your screen content
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            ImageCarousel(images = product.photos)
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AddToFavButton(
                isFavorite = isFavorite,
                onFavoriteClick = onFavoriteClick
            )
            ProductPriceSection(prices = product.prices)
            PropertyList(product.mainProperties)
        }
    }

}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}


@Composable
fun PropertyRow(propName: String, propValue: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        Text(
            text = propName,
            modifier = Modifier.weight(1f),
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            text = propValue,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PropertyList(properties: List<MainProperty>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Основное",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        properties.forEach { property ->
            PropertyRow(propName = property.propName, propValue = property.propValue)
        }
        Text(
            text = "Все характеристики",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.End)
        )
    }
}