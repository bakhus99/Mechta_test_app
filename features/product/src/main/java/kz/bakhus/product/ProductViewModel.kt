package kz.bakhus.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kz.bakhus.core.repository.FavouritesRepository
import kz.bakhus.product_data.ProductRepository
import kz.bakhus.product_data.models.Product
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: Provider<ProductUseCase>,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    private val _productState = MutableStateFlow<ProductState>(ProductState.Loading)
    val productState: StateFlow<ProductState> = _productState

    private val _navigationCommands = MutableSharedFlow<NavigationCommand>()
    val navigationCommands = _navigationCommands.asSharedFlow()

    private val _favorites = favouritesRepository.favoriteProducts
    val favorites: StateFlow<List<String>> = _favorites.stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    fun fetchProduct(productId: String) {
        viewModelScope.launch {
            _productState.value = ProductState.Loading
            val result = productUseCase.get().invoke(productId)
            _productState.value = when {
                result.isSuccess -> ProductState.Success(result.getOrNull()!!)
                result.isFailure -> ProductState.Error(
                    result.exceptionOrNull()?.message ?: "Unknown error"
                )

                else -> ProductState.Error("Unknown state")
            }
        }
    }


    fun navigateBack() {
        viewModelScope.launch {
            _navigationCommands.emit(NavigationCommand.Back)
        }
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

    sealed class NavigationCommand {
        data object Back : NavigationCommand()
    }

    sealed class ProductState {
        data object Loading : ProductState()
        data class Success(val product: Product) : ProductState()
        data class Error(val message: String) : ProductState()
    }
}
