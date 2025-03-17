package dev.vengateshm.autogenerateretrofitapicode

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import dev.vengateshm.autogenerateretrofitapicode.auth.AuthApiService
import dev.vengateshm.autogenerateretrofitapicode.posts.PostsApiService
import dev.vengateshm.autogenerateretrofitapicode.users.UsersApiService

private val retrofit: Retrofit by lazy {
    val loggingInterceptor =
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    val contentType = "application/json".toMediaType()
    val json =
        Json {
            ignoreUnknownKeys = true
        }
    Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}

val authApiService: AuthApiService by lazy {
    retrofit.create(AuthApiService::class.java)
}

val usersApiService: UsersApiService by lazy {
    retrofit.create(UsersApiService::class.java)
}

val postsApiService: PostsApiService by lazy {
    retrofit.create(PostsApiService::class.java)
}
