package dev.vengateshm.retrofitcodegen

import dev.vengateshm.retrofitcodegen.models.Config
import kotlinx.serialization.json.Json

fun parseConfig(json: String): Config {
    return Json.decodeFromString(json)
}
