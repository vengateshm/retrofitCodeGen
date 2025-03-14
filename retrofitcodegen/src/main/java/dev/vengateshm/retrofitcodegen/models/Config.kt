package dev.vengateshm.retrofitcodegen.models

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val baseUrl: String,
    val endpoints: List<EndPoint>,
    val models: Map<String, Map<String, String>>,
)
