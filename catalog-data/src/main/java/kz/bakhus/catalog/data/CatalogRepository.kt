package kz.bakhus.catalog.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kz.bakhus.api.Api
import kz.bakhus.catalog.data.models.CatalogItem
import kz.bakhus.database.Database

class CatalogRepository @Inject constructor(
    private val api: Api,
) {


    fun getCatalogData(): Flow<PagingData<CatalogItem>> {
        return Pager(
            config = PagingConfig(pageSize = 24, prefetchDistance = 2),
            pagingSourceFactory = {
                CatalogPaggingSource(api)
            }
        ).flow
    }


}
