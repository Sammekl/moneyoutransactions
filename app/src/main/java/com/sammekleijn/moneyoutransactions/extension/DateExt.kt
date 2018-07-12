package com.sammekleijn.moneyoutransactions.extension

import android.text.format.DateFormat
import com.sammekleijn.moneyoutransactions.MainApplication
import java.util.*

fun Date.toDateTimeString(): String {
    val context = MainApplication.instance.applicationContext

    val dateFormat = DateFormat.getDateFormat(context)
    val timeFormat = DateFormat.getTimeFormat(context)

    return "${dateFormat.format(this)} ${timeFormat.format(this)}"
}