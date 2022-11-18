package com.example.sorixjson.model

import com.example.amphibians.network.NullToEmptyObject
import com.example.amphibians.network.NullToEmptyString
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
    @Json(name = "inputElements") @NullToEmptyObject val inputElements: List<InputElement> = listOf<InputElement>()
)

data class Link(
    @Json(name = "logo") @NullToEmptyString var logo: String = "img_placeholder",
)

data class InputElement(
    @Json(name = "name") @NullToEmptyString var name: String = "",
    @Json(name = "type") @NullToEmptyString var type: String = "",
)