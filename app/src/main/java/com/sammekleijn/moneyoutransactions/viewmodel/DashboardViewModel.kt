package com.sammekleijn.moneyoutransactions.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.model.CustomerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DashboardViewModel(val customerModel: CustomerModel = CustomerModel()) : ViewModel() {

    val isLoading = ObservableField<Boolean>()

    val customer = MutableLiveData<Customer>()

    private val compositeDisposable = CompositeDisposable()

    fun loadCustomer() {
        isLoading.set(true)

        compositeDisposable.add(customerModel.get()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    customer.value = data
                }, { error ->
                    // Empty
                }, {
                    isLoading.set(false)
                }))
    }

    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
