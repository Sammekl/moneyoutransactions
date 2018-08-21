package com.sammekleijn.moneyoutransactions.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sammekleijn.moneyoutransactions.domain.Transaction

class TransactionDetailModel : ViewModel() {

    val transaction = MutableLiveData<Transaction>()

    fun show(transaction: Transaction) {
        this.transaction.value = transaction
    }

}
