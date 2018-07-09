package com.sammekleijn.moneyoutransactions.model

data class Customer(val account: String, var balance: Float, val transactions: List<Transaction>)