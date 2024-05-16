package kz.bakhus.api.models.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemDTO(
   @SerialName("availability") val availability: String,
   @SerialName("digital") val digital: Boolean,
   @SerialName("id") val id: Int,
   @SerialName("in_favorites") val inFavorites: Boolean,
   @SerialName("name") val name: String,
   @SerialName("new_item") val newItem: Boolean,
   @SerialName("photos") val photos: List<String>,
   @SerialName("price") val price: Int,
   @SerialName("prices") val prices: PricesDTO,
   @SerialName("rating") val rating: Double,
   @SerialName("reviews_count") val reviewsCount: Int,
   @SerialName("service") val service: Boolean,
   @SerialName("title") val title: String,
   @SerialName("xml_id") val xmlId: String,
   @SerialName("code") val code: String,
)