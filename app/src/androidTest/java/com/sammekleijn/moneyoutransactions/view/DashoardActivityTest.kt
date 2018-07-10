package com.sammekleijn.moneyoutransactions.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.injection.module.TestServiceModule
import com.sammekleijn.moneyoutransactions.model.Customer
import com.sammekleijn.moneyoutransactions.service.CustomerService
import io.reactivex.Single
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DashoardActivityTest: BaseActivityTest() {
    @Rule
    @JvmField
    val dashoardActivity = ActivityTestRule(DashoardActivity::class.java, true, false)

    val customerService: CustomerService = mock(CustomerService::class.java)

    @Before
    fun setup() {
        dashoardActivity.launchActivity(null)
        dashoardActivity.activity.customerService = customerService

    }

    override fun setupMocks(testServiceModule: TestServiceModule) {
        testServiceModule.customerService = customerService
    }

    @Test
    fun showsAccountBalance() {
        val customer = Customer("123", 500.71f, mutableListOf())
        `when`(customerService.getCustomer()).thenReturn(Single.just(customer))

        onView(withId(R.id.accountBalanceTextView)).check(matches(withText("€${customer.balance}")))
    }
}