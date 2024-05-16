package kz.bakhus.catalog.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import jakarta.inject.Inject
import kz.bakhus.api.Api
import kz.bakhus.api.models.catalog.ItemDTO
import kz.bakhus.catalog.data.models.Catalog
import kz.bakhus.catalog.data.models.CatalogItem
import java.io.IOException


class CatalogPaggingSource @Inject constructor(
    private val remoteDataSource: Api,
) : PagingSource<Int, CatalogItem>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatalogItem> {
        return try {
            val currentPage = params.key ?: 1
            val catalog = remoteDataSource.getCatalog(
                page = currentPage,
                pageLimit = 24
            ).catalogData.toCatalog()
            LoadResult.Page(
                data = catalog.items,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (catalog.items.isEmpty()) null else catalog.pageNumber + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatalogItem>): Int? {
        return state.anchorPosition
    }

}