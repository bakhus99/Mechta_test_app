package kz.bakhus.catalog.main

import kotlinx.coroutines.flow.Flow
import kz.bakhus.catalog.data.CatalogRepository
import kz.bakhus.catalog.data.models.Catalog
import kz.bakhus.catalog.data.models.CatalogItem
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(private val repository: CatalogRepository) {
    operator  fun invoke(): Flow<androidx.paging.PagingData<CatalogItem>> {
        return repository.getCatalogData()
    }

}