package com.example.sorixjson.network

import com.example.sorixjson.model.SomeData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://gist.githubusercontent.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(NullToEmptyListAdapter())
    .add(NullToEmptyStringAdapter())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SorixApiService {
    @GET("Aeforeva/b5784ef0872236ce73426bd5cbe8872b/raw/953feba1730dbbc5f5273d56dcca820bcd00d95a/sorix.json")
    suspend fun getSorixJson(): SomeData
}

object SorixApi {
    val retrofitService: SorixApiService by lazy {
        retrofit.create(SorixApiService::class.java)
    }
}