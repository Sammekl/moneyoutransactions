package com.sammekleijn.moneyoutransactions.extension

fun Float.toStringWithPrecision(precision: Int): String {
    return "%.${precision}f".format(this)
}

fun Float.toEuro(precision: Int = 2): String {
    return if (this > 0f) {
        "+ €${this.toStringWithPrecision(precision)}"
    } else {
        "- €${this.toStringWithPrecision(precision)}"
    }
}