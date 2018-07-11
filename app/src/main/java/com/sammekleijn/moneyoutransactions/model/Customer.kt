package com.sammekleijn.moneyoutransactions.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Customer(val account: String, var balance: Float, var transactions: List<Transaction>) : Parcelable {

    fun getBalanceAt(date: Date): Float {
        val transactionsAfterDate = transactions.filter { it.date > date }

        val totalAmount = transactionsAfterDate.sumByDouble { it.amount.toDouble() }.toFloat()

        return balance - totalAmount
    }
}