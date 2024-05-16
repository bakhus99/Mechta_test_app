package kz.bakhus.core.models

data class Prices(
    val basePrice: Int,
    val discountedPrice: Int,
    val hasDiscount: Boolean
)