package com.sammekleijn.moneyoutransactions.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.model.CustomerModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var dashboardViewModel: DashboardViewModel
    lateinit var customerModel: CustomerModel

    @Before
    fun setup() {
        customerModel = mock()
        dashboardViewModel = DashboardViewModel(customerModel)
    }

    @Test
    fun testObserveCustomerRetrieval() {
        val observer = mock<Observer<Customer>>()
        val customer = Customer("My account", 5f, mutableListOf())
        dashboardViewModel.customer.observeForever(observer)

        given(customerModel.get()).willReturn(Observable.just(customer))

        dashboardViewModel.loadCustomer()

        verify(observer).onChanged(customer)
    }

    @Test
    fun testObserveErrorThrown() {
        val observer = mock<Observer<String>>()
        val error = IllegalArgumentException("Hey! This is not allowed")
        dashboardViewModel.errorMessage.observeForever(observer)

        given(customerModel.get()).willReturn(Observable.error(error))

        dashboardViewModel.loadCustomer()

        verify(observer).onChanged(error.message)
    }
}