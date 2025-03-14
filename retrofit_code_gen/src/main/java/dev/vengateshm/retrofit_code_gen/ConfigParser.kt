package dev.vengateshm.retrofit_code_gen

import dev.vengateshm.retrofit_code_gen.models.Config
import kotlinx.serialization.json.Json

fun parseConfig(json: String): Config {
    return Json.decodeFromString(json)
}