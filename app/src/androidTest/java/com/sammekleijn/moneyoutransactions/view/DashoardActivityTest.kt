package com.sammekleijn.moneyoutransactions.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.extension.launch
import com.sammekleijn.moneyoutransactions.injection.TestApplicationComponent
import com.sammekleijn.moneyoutransactions.injection.TestServiceModule
import com.sammekleijn.moneyoutransactions.matcher.EspressoTestMatchers.withIndex
import com.sammekleijn.moneyoutransactions.model.Customer
import com.sammekleijn.moneyoutransactions.model.Transaction
import com.sammekleijn.moneyoutransactions.service.CustomerService
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

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
    fun showsAccountData() {
        val customer = Customer("123", 500.71f, mutableListOf())
        `when`(customerService.getCustomer()).thenReturn(Single.just(customer))

        dashoardActivity.launch()

        onView(withId(R.id.accountBalanceTextView)).check(matches(withText("€${customer.balance}")))
        onView(withId(R.id.accountNumberTextView)).check(matches(withText(customer.account)))
    }


    @Test
    fun showsTransactions() {
        val customer = Customer("123", 500.71f, mutableListOf(
                Transaction("t1", 10.22f, "", "Counterparty1", Date(), null),
                Transaction("t2", -0.39f, "", "Counterparty2", Date(), null)
        ))
        `when`(customerService.getCustomer()).thenReturn(Single.just(customer))

        dashoardActivity.launch()

        onView(withIndex(withId(R.id.otherAccountTextView), 0)).check(matches(withText("Counterparty1")))
        onView(withIndex(withId(R.id.amountTextView), 0)).check(matches(withText("+ €10.22")))

        onView(withIndex(withId(R.id.otherAccountTextView), 1)).check(matches(withText("Counterparty2")))
        onView(withIndex(withId(R.id.amountTextView), 1)).check(matches(withText("- €0.39")))
    }

}