package com.sammekleijn.moneyoutransactions.domain

import android.os.Parcelable
import com.sammekleijn.moneyoutransactions.extension.toEuro
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Transaction(val id: String, val amount: Float, val description: String?, val otherAccount: String, val date: Date, var balanceAfterTransaction: Float?) : Parcelable {

    fun amountInEuro() = amount.toEuro(2)
}