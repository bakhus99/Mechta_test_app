package kz.bakhus.product

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.bakhus.product_data.ProductRepository
import kz.bakhus.product_data.models.Product
import okhttp3.Response
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(productId: String): Result<Product> {
        return repository.getProduct(productId)
    }

}