package com.example.amphibians.network

import android.util.Log
import com.example.sorixjson.model.InputElement
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import org.jetbrains.annotations.Nullable

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyObject

class NullToEmptyObjectAdapter {
    @ToJson
    fun toJson(@NullToEmptyObject value: List<InputElement>?): List<InputElement>? {
        return value
    }

    @FromJson
    @NullToEmptyObject
    fun fromJson(@Nullable data: List<InputElement>?): List<InputElement>? {
        return data ?: listOf()
    }
}