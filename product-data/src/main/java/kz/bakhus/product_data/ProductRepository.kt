package kz.bakhus.product_data

import jakarta.inject.Inject
import kz.bakhus.api.Api
import kz.bakhus.database.Database
import kz.bakhus.product_data.models.Product

class ProductRepository @Inject constructor(
    private val api: Api,
) {

    suspend fun getProduct(productId: String, cacheCity: String? = "s1"): Result<Product> {
        return try {
            val response = api.getProduct(productId, cacheCity)
            if (response.isSuccess) {
                val productDataDTO = response.getOrThrow().catalogData
                Result.success(productDataDTO.toProduct())
            } else {
                Result.failure(Exception("Error: ${response.getOrNull()?.errors}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}