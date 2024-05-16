package kz.bakhus.api.models.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionDTO(
   @SerialName("code") val code: String,
   @SerialName("name") val name: String
)