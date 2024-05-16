package kz.bakhus.api

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import kz.bakhus.api.models.CatalogSections
import kz.bakhus.api.models.catalog.CatalogDataDTO
import kz.bakhus.api.models.Response
import kz.bakhus.api.models.product.ProductDataDTO
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    /**
     * Request detail [click](https://www.mechta.kz/api/v1/catalog?page=1&page_limit=24&section=smartfony)
     */
    @GET("catalog")
    suspend fun getCatalog(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("page_limit") @IntRange(from = 0, to = 24) pageLimit: Int = 24,
        @Query("section") sections:String? = CatalogSections.SMARTPHONES.serializedName,
    ): Response<CatalogDataDTO>

    /**
     * Request detail [click](https://www.mechta.kz/api/v1/product/telefon-sotovyy-samsung-sm-a-135-galaxy-a13-64gb-flbvs-blue?cache_city=s1)
     */
    @GET("product/{productId}")
    suspend fun getProduct(
        @Path("productId") productId: String,
        @Query("cache_city") cacheCity: String? = "s1"
    ): Result<Response<ProductDataDTO>>
}

fun Api(
    baseUrl: String,
    json:Json = Json
): Api {

    val jsonConverterFactory = json.asConverterFactory("application/parse".toMediaType())

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        )
        .build()

    return retrofit.create()
}

