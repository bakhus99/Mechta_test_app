package kz.bakhus.product_data.models

import kz.bakhus.core.models.Prices

data class Product(
    val id: Int,
    val inFavorites: Boolean,
    val mainProperties: List<MainProperty>,
    val name: String,
    val photos: List<String>,
    val price: Int,
    val prices: Prices,
    val title: String,
    val code: String,
)


data class MainProperty(
    val code: String,
    val propId: Int,
    val propName: String,
    val propNameDescription: String,
    val propValue: String,
)