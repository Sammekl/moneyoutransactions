package com.sammekleijn.moneyoutransactions.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.extension.launch
import com.sammekleijn.moneyoutransactions.injection.ApplicationComponent
import com.sammekleijn.moneyoutransactions.injection.ServiceLocator
import com.sammekleijn.moneyoutransactions.injection.TestApplicationComponent
import com.sammekleijn.moneyoutransactions.injection.TestServiceModule
import com.sammekleijn.moneyoutransactions.model.Customer
import com.sammekleijn.moneyoutransactions.service.CustomerService
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DashoardActivityTest : BaseActivityTest() {

    @Rule
    @JvmField
    val dashoardActivity = ActivityTestRule(DashoardActivity::class.java, true, false)

    lateinit var customerService: CustomerService

    override fun setupMocks(testServiceModule: TestServiceModule) {
        this.customerService = mock(CustomerService::class.java)
        testServiceModule.customerService = customerService
    }

    override fun doInject(testApplicationComponent: TestApplicationComponent) {
        testApplicationComponent.inject(this)
    }

    @Test
    fun showsAccountBalance() {
        val customer = Customer("123", 500.71f, mutableListOf())
        `when`(customerService.getCustomer()).thenReturn(Single.just(customer))

        dashoardActivity.launch()

        onView(withId(R.id.accountBalanceTextView)).check(matches(withText("€${customer.balance}")))
    }
}