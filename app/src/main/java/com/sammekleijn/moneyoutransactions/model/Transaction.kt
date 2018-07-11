package com.sammekleijn.moneyoutransactions.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Transaction(val id: String, val amount: Float, val description: String?, val otherAccount: String, val date: Date, var balanceAfterTransaction: Float?) : Parcelable