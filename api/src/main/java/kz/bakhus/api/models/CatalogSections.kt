package kz.bakhus.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
enum class CatalogSections(val serializedName: String) {
    @SerialName("smartfony")
    SMARTPHONES("smartfony"),
    //todo add another parameters

}