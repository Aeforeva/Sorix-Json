package com.example.sorixjson.network

import com.example.sorixjson.model.InputData
import com.example.sorixjson.model.SomeData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


private const val BASE_URL =
    "https://gist.githubusercontent.com"

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .add(NullToEmptyListAdapter())
    .add(NullToEmptyStringAdapter())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private val moshiTest = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val sendTest = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshiTest))
    .baseUrl("https://06d9a76b-6e4d-4750-b376-a4298e0d37f5.mock.pstmn.io") // Postman mock
    .build()

interface SorixApiService {
    @GET("Aeforeva/b5784ef0872236ce73426bd5cbe8872b/raw/953feba1730dbbc5f5273d56dcca820bcd00d95a/sorix.json")
    suspend fun getSorixJson(): SomeData

    @POST("/post")
    suspend fun postJson(@Body inputData: InputData): Response<InputData>

    @FormUrlEncoded
    @PUT("/put")
    suspend fun putUrlEncoded(
        @Field("number") number: Int?,
        @Field("expiryMonth") expiryMonth: Int?,
        @Field("expiryYear") expiryYear: Int?,
        @Field("verificationCode") verificationCode: Int?,
        @Field("holderName") holderName: String?,
        @Field("iban") iban: String?,
        @Field("bic") bic: String?
    ): Response<InputData>
}

object SorixApi {
    val retrofitService: SorixApiService by lazy {
        retrofit.create(SorixApiService::class.java)
    }
    val retrofitServiceSend: SorixApiService by lazy {
        sendTest.create(SorixApiService::class.java)
    }
}