package com.sammekleijn.moneyoutransactions.extension

import java.io.InputStream
import java.nio.charset.Charset

fun InputStream.readString(): String {
    val size = available()
    val buffer = ByteArray(size)
    read(buffer)
    close()
    return String(buffer, Charset.forName("UTF-8"))
}