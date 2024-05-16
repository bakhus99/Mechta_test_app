package kz.bakhus.mechtatestapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kz.bakhus.api.Api
import kz.bakhus.core.AppDispatchers
import kz.bakhus.database.Database
import kz.bakhus.core.repository.FavouritesRepository
import kz.bakhus.mechtatestapp.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): Api {
        return Api(
            baseUrl = BuildConfig.API_URL,
            json = Json {
                ignoreUnknownKeys = true
            }
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Database(context)
    }

    @Provides
    @Singleton
    fun provideAppCoroutineDispatchers(): AppDispatchers {
        return AppDispatchers()
    }


}