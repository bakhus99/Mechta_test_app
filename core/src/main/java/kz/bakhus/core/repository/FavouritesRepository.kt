package kz.bakhus.core.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.bakhus.database.Database
import kz.bakhus.database.modules.FavoriteProductEntity
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val database: Database,
){

    val favoriteProducts: Flow<List<String>> =
        database.favouritesDao().getFavoriteProducts().map { entities ->
            entities.map { it.productId }
        }

    suspend fun addFavorite(productId: String) {
        database.favouritesDao().insertFavorite(FavoriteProductEntity(productId))
    }

    suspend fun removeFavorite(productId: String) {
        database.favouritesDao().deleteFavorite(productId)
    }

}