package kz.bakhus.api.models.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.bakhus.api.models.catalog.PricesDTO

@Serializable
data class ProductDataDTO(
    @SerialName("code") val code: String,
    @SerialName("code_1c") val code1c: String,
    @SerialName("id") val id: Int,
    @SerialName("in_compare") val inCompare: Boolean,
    @SerialName("in_favorites") val inFavorites: Boolean,
    @SerialName("main_properties") val mainProperties: List<MainPropertyDTO>,
    @SerialName("name") val name: String,
    @SerialName("photos") val photos: List<String>,
    @SerialName("price") val price: Int,
    @SerialName("prices") val prices: PricesDTO,
    @SerialName("rating") val rating: Int,
    @SerialName("reviews_count") val reviewsCount: Int,
    @SerialName("same_product_properties") val sameProductProperties: List<String>,
    @SerialName("title") val title: String,
    @SerialName("xml_id") val xmlId: String,
)