package com.sammekleijn.moneyoutransactions.extension

import com.google.gson.Gson
import java.io.InputStream

fun <T> Gson.fromInputStream(inputStream: InputStream, ofType: Class<T>): T {
    val json = inputStream.readString()
    return fromJson<T>(json, ofType)
}