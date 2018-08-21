package com.sammekleijn.moneyoutransactions.model

import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.domain.Transaction
import com.sammekleijn.moneyoutransactions.extension.fromJson
import io.reactivex.Observable

class CustomerModel {
    fun get(): Observable<Customer> {
        val applicationContext = MainApplication.instance.applicationContext
        val inputStream = applicationContext.assets.open("transactions.json")

        return Observable.fromCallable {
            inputStream.fromJson(Customer::class.java)
        }.map {customer ->
            customer.transactions = customer.transactions.sortedByDescending { it.date }.toMutableList()
            customer.transactions.forEach { transaction ->
                transaction.balanceAfterTransaction = getBalanceAfterTransaction(customer, transaction)
            }
            customer
        }
    }

    fun getBalanceAfterTransaction(customer: Customer, transaction: Transaction): Float {
        val transactionsAfterDate = customer.transactions.filter { it.date > transaction.date }

        val totalAmount = transactionsAfterDate.sumByDouble { it.amount.toDouble() }.toFloat()

        return customer.balance - totalAmount
    }
}