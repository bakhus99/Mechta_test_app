package kz.bakhus.catalog.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.bakhus.catalog.data.models.CatalogItem
import kz.bakhus.core.repository.FavouritesRepository
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
internal class CatalogMainViewModel @Inject constructor(
    private val getCatalogUseCase: Provider<GetCatalogUseCase>,
    private val favouritesRepository: FavouritesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<PagingData<CatalogItem>> =
        MutableStateFlow(value = PagingData.empty())
    val state: MutableStateFlow<PagingData<CatalogItem>> get() = _state

    private val _favorites = favouritesRepository.favoriteProducts
    val favorites: StateFlow<List<String>> = _favorites.stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    init {
        getMovies()
    }

    fun addFavorite(productId: String) {
        viewModelScope.launch {
            favouritesRepository.addFavorite(productId)
        }
    }

    fun removeFavorite(productId: String) {
        viewModelScope.launch {
            favouritesRepository.removeFavorite(productId)
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            getCatalogUseCase.get().invoke()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _state.value = it
                }

        }
    }
}


