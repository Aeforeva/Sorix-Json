package com.example.sorixjson.model

import com.example.sorixjson.network.NullToEmptyList
import com.example.sorixjson.network.NullToEmptyString
import com.squareup.moshi.Json

data class SomeData(
    val networks: SomeNetwork,
)

data class SomeNetwork(
    val applicable: List<Applicable>
)

data class Applicable(
    @Json(name = "code") @NullToEmptyString var code: String = "",
    @Json(name = "label") @NullToEmptyString var label: String = "",
    val links: Link,
    @Json(name = "inputElements") @NullToEmptyList val inputElements: List<InputElement> = listOf()

    /** Alternatively with null instead of empty List */
//    @Json(name = "inputElements")val inputElements: List<InputElement>?
)

data class Link(
    // @NullToEmptyString not really empty here
    @Json(name = "logo") @NullToEmptyString var logo: String = "https://placehold.jp/156x96.png",
)

data class InputElement(
    @Json(name = "name") @NullToEmptyString var name: String = "",
    @Json(name = "type") @NullToEmptyString var type: String = "",
)