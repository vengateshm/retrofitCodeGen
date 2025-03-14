package dev.vengateshm.retrofit_code_gen.models

import kotlinx.serialization.Serializable

@Serializable
data class EndPoint(
    val name: String,
    val path: String,
    val method: String,
    val requestModel: String? = null,
    val responseModel: String,
)
