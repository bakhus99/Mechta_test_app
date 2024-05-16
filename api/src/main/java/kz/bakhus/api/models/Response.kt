package kz.bakhus.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response<E>(
    @SerialName("data")
    val catalogData: E,
    @SerialName("errors")
    val errors: List<String>,
    @SerialName("result")
    val result: Boolean
)