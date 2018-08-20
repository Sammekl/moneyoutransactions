package com.sammekleijn.moneyoutransactions.model

import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.extension.fromJson
import io.reactivex.Observable

class CustomerModel {
    fun get(): Observable<Customer> {
        val applicationContext = MainApplication.instance.applicationContext
        val inputStream = applicationContext.assets.open("transactions.json")

        return Observable.fromCallable {
            inputStream.fromJson(Customer::class.java)
        }.map {
            it.transactions = it.transactions.sortedByDescending { it.date }.toMutableList()
            it
        }
    }

//    fun getBalanceAt(date: Date): Float {
//        val transactionsAfterDate = transactions.filter { it.date > date }
//
//        val totalAmount = transactionsAfterDate.sumByDouble { it.amount.toDouble() }.toFloat()
//
//        return balance - totalAmount
//    }
}