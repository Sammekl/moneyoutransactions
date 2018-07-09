package com.sammekleijn.moneyoutransactions.extension

import com.google.gson.Gson
import java.io.InputStream
import java.nio.charset.Charset


fun <T> InputStream.fromJson(ofType: Class<T>): T {
    val json = readString()
    return Gson().fromJson<T>(json, ofType)
}

fun InputStream.readString(): String {
    val size = available()
    val buffer = ByteArray(size)
    read(buffer)
    close()
    return String(buffer, Charset.forName("UTF-8"))
}