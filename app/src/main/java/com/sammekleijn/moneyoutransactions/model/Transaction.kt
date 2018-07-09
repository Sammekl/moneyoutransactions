package com.sammekleijn.moneyoutransactions.model

import java.util.*

data class Transaction(val id: String, val amount: Float, val description: String?, val otherAccount: String, val date: Date)