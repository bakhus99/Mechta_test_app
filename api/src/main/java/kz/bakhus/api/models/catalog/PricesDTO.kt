package kz.bakhus.api.models.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PricesDTO(
   @SerialName("base_price") val basePrice: Int,
   @SerialName("discounted_price") val discountedPrice: Int,
   @SerialName("has_discount") val hasDiscount: Boolean
)