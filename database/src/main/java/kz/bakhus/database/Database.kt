package kz.bakhus.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.bakhus.database.dao.FavouritesDao
import kz.bakhus.database.modules.FavoriteProductEntity

@Database(entities = [FavoriteProductEntity::class], version = 4, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}

fun Database(applicationContext: Context): kz.bakhus.database.Database {
    return Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        kz.bakhus.database.Database::class.java,
        "catalog"
    ).build()
}