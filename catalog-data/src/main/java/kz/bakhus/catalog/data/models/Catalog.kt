package kz.bakhus.catalog.data.models

import kz.bakhus.core.models.Prices

data class Catalog(
    val allItemsCount: Int,
    val items: List<CatalogItem>,
    val pageItemsCount: Int,
    val pageNumber: Int,
)

data class SectionDBO(
    val code: String,
    val name: String
)

data class CatalogItem(
    val id: Int,
    val inFavorites: Boolean,
    val name: String,
    val newItem: Boolean,
    val photos: List<String>,
    val price: Int,
    val prices: Prices,
    val rating: Int,
    val reviewsCount: Int,
    val title: String,
    val code: String,
)

