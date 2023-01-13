package com.example.sorixjson.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class Response(
    var number: Int? = null,
    var expiryMonth: Int? = null,
    var expiryYear: Int? = null,
    var verificationCode: Int? = null,
    var holderName: String? = null,
    @Json(name = "IBAN") var iban: String? = null,
    @Json(name = "BIC") var bic: String? = null
)