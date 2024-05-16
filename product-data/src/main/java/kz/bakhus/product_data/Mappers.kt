package kz.bakhus.product_data

import kz.bakhus.api.models.catalog.PricesDTO
import kz.bakhus.api.models.product.MainPropertyDTO
import kz.bakhus.api.models.product.ProductDataDTO
import kz.bakhus.core.models.Prices
import kz.bakhus.product_data.models.MainProperty
import kz.bakhus.product_data.models.Product


internal fun ProductDataDTO.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        prices = prices.toPrices(),
        inFavorites = inFavorites,
        photos = photos,
        price = price,
        title = title,
        mainProperties = mainProperties.map { it.toMainProperty() },
        code = code
    )


}

internal fun MainPropertyDTO.toMainProperty(): MainProperty {
    return MainProperty(
        code = code,
        propId = propId,
        propName = propName,
        propValue = propValue,
        propNameDescription = propNameDescription,

        )
}

internal fun PricesDTO.toPrices(): Prices {
    return Prices(
        basePrice, discountedPrice, hasDiscount,

        )
}