package com.sammekleijn.moneyoutransactions.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.injection.ServiceLocator
import com.sammekleijn.moneyoutransactions.model.CustomerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DashboardViewModel: ViewModel() {

    @Inject
    lateinit var customerModel: CustomerModel

    val isLoading = ObservableField<Boolean>()

    val customer = MutableLiveData<Customer>()

    val errorMessage = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    init {
        ServiceLocator.applicationComponent?.inject(this)
    }

    fun loadCustomer() {
        isLoading.set(true)

        compositeDisposable.add(customerModel.get()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    customer.value = data
                }, { error ->
                    errorMessage.value = error.message
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
