package com.sammekleijn.moneyoutransactions.domain

import android.os.Parcelable
import com.sammekleijn.moneyoutransactions.extension.toDateTimeString
import com.sammekleijn.moneyoutransactions.extension.toEuro
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Transaction(val id: String, val amount: Float, val description: String?, val otherAccount: String, val date: Date, var balanceAfterTransaction: Float?) : Parcelable {

    fun amountInEuro() = amount.toEuro(2)
    fun dateAsString() = date.toDateTimeString()

    fun balanceBeforeTransaction(): Float {
        return balanceAfterTransaction?.let {
            it + amount.unaryMinus()
        } ?: 0f
    }
}