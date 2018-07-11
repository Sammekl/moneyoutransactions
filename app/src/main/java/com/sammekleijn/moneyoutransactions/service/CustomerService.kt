package com.sammekleijn.moneyoutransactions.service

import android.content.Context
import com.sammekleijn.moneyoutransactions.extension.fromJson
import com.sammekleijn.moneyoutransactions.model.Customer
import io.reactivex.Single

open class CustomerService(val context: Context) {

    open fun getCustomer(): Single<Customer> {
        val inputStream = context.assets.open("transactions.json")

        return Single.fromCallable {
            inputStream.fromJson(Customer::class.java)
        }.map {
            it.transactions = it.transactions.sortedByDescending { it.date }
            it
        }
    }
}