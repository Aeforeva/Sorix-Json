package com.example.sorixjson.network

import com.example.sorixjson.model.InputElement
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyList

class NullToEmptyListAdapter {
    @ToJson
    fun toJson(@NullToEmptyList value: List<InputElement>?): List<InputElement>? {
        return value
    }

    @FromJson
    @NullToEmptyList
    fun fromJson(data: List<InputElement>?): List<InputElement> {
        return data ?: listOf()
    }
}