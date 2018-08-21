package com.sammekleijn.moneyoutransactions.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.extension.plusAssign
import com.sammekleijn.moneyoutransactions.injection.ServiceLocator
import com.sammekleijn.moneyoutransactions.model.CustomerModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DashboardViewModel() : ViewModel() {

    constructor(customerModel: CustomerModel) : this() {
        this.customerModel = customerModel
    }

    @Inject
    lateinit var customerModel: CustomerModel

    val isLoading = ObservableField<Boolean>(false)

    val customer = MutableLiveData<Customer>()

    val errorMessage = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    init {
        ServiceLocator.applicationComponent?.inject(this)
    }

    fun loadCustomer(scheduler: Scheduler = Schedulers.newThread()) {
        isLoading.set(true)

        compositeDisposable += customerModel.get()
                .subscribeOn(scheduler)
                .subscribe({ data ->
                    customer.postValue(data)
                }, { error ->
                    errorMessage.postValue(error.message)
                }, {
                    isLoading.set(false)
                })
    }

    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
