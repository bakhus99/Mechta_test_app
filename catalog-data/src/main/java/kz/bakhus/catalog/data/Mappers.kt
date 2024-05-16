package kz.bakhus.catalog.data

import kz.bakhus.api.models.catalog.CatalogDataDTO
import kz.bakhus.api.models.catalog.ItemDTO
import kz.bakhus.api.models.catalog.PricesDTO
import kz.bakhus.catalog.data.models.Catalog
import kz.bakhus.catalog.data.models.CatalogItem
import kz.bakhus.core.models.Prices


internal fun CatalogDataDTO.toCatalog(): Catalog {
    return Catalog(
        allItemsCount = allItemsCount,
        items = items.map { it.toItem() }.toList(),
        pageNumber = pageNumber,
        pageItemsCount = pageItemsCount,

        )
}

internal fun ItemDTO.toItem(): CatalogItem {
    return CatalogItem(
        id = id,
        inFavorites = inFavorites,
        name = name,
        newItem = newItem,
        photos = photos,
        price = price,
        prices = prices.toPrices(),
        rating = rating.toInt(),
        reviewsCount = reviewsCount,
        title = title,
        code = code,
    )
}

internal fun PricesDTO.toPrices(): Prices {
    return Prices(
        basePrice, discountedPrice, hasDiscount,

        )
}

