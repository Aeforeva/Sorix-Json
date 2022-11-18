package com.example.sorixjson.model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://gist.githubusercontent.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SorixApiService {
    @GET("Sorix/edec261a605373584695b0708ad8ec72/raw/e4ff947b59f01c689dea617eaa23e8ed5a8fde94/wechat.json")
    suspend fun getJson(): String
}

object SorixApi {
    val retrofitService : SorixApiService by lazy {
        retrofit.create(SorixApiService::class.java) }
}