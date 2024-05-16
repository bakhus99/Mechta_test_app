package kz.bakhus.api.models.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainPropertyDTO(
    @SerialName("code") val code: String,
    @SerialName("prop_id") val propId: Int,
    @SerialName("prop_name") val propName: String,
    @SerialName("prop_name_description") val propNameDescription: String,
    @SerialName("prop_value") val propValue: String,
)