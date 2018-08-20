package com.sammekleijn.moneyoutransactions.domain

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.sammekleijn.moneyoutransactions.BR

class Customer(val account: String, balance: Float, var transactions: MutableList<Transaction>): BaseObservable(){

    @get:Bindable
    var balance: Float = 0f
        set(value) {
            field = value
            notifyPropertyChanged(BR.balance)
        }
}