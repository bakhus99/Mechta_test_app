package kz.bakhus.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kz.bakhus.database.modules.FavoriteProductEntity

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteProduct: FavoriteProductEntity)

    @Query("DELETE FROM favorite_products WHERE productId = :productId")
    suspend fun deleteFavorite(productId: String)

    @Query("SELECT * FROM favorite_products")
    fun getFavoriteProducts(): Flow<List<FavoriteProductEntity>>
}