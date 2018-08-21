package com.sammekleijn.moneyoutransactions.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.domain.Transaction
import com.sammekleijn.moneyoutransactions.extension.launch
import com.sammekleijn.moneyoutransactions.extension.toEuro
import com.sammekleijn.moneyoutransactions.injection.TestApplicationComponent
import com.sammekleijn.moneyoutransactions.injection.TestServiceModule
import com.sammekleijn.moneyoutransactions.matcher.EspressoTestMatchers.withIndex
import com.sammekleijn.moneyoutransactions.model.CustomerModel
import io.reactivex.Observable
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.IOException
import java.util.*

class DashboardActivityTest : BaseActivityTest() {

    @Rule
    @JvmField
    val dashoardActivity = ActivityTestRule(DashboardActivity::class.java, true, false)

    lateinit var customerModel: CustomerModel

    override fun setupMocks(testServiceModule: TestServiceModule) {
        this.customerModel = mock(CustomerModel::class.java)
        testServiceModule.customerModel = customerModel
    }

    override fun doInject(testApplicationComponent: TestApplicationComponent) {
        testApplicationComponent.inject(this)
    }

    @Test
    fun showsAccountData() {
        val customer = givenACustomer()

        dashoardActivity.launch()

        onView(withId(R.id.accountBalanceTextView)).check(matches(withText("€${customer.balance}")))
        onView(withId(R.id.accountNumberTextView)).check(matches(withText(customer.account)))
    }


    @Test
    fun showsTransactions() {
        val customer = givenACustomer()

        dashoardActivity.launch()

        customer.transactions.forEachIndexed { index, transaction ->
            onView(withIndex(withId(R.id.otherAccountTextView), index)).check(matches(withText(transaction.otherAccount)))
            onView(withIndex(withId(R.id.amountTextView), index)).check(matches(withText(transaction.amount.toEuro(2))))
        }
    }

    @Test
    fun showsErrorWhenGetCustomerFails() {
        val errorMessage = MainApplication.instance.applicationContext.getString(R.string.account_retrieval_failed)
        givenAnError(errorMessage)

        dashoardActivity.launch()

        verifySnackBarIsShown(errorMessage)
    }

    @Test
    fun opensDetailWhenClickingTransaction() {
        val customer = givenACustomer()

        dashoardActivity.launch()

        Intents.init()
        onView(withText(customer.transactions[0].otherAccount)).perform(click())
        intended(allOf(
                hasComponent(TransactionDetailActivity::class.java.name), hasExtra(TransactionDetailActivity.TRANSACTION_EXTRA, customer.transactions[0])
        ))

        Intents.release()

    }

    private fun givenACustomer(): Customer {
        val customer = Customer("123", 500.71f, mutableListOf(
                Transaction("t1", 10.22f, "", "Counterparty1", Date(), null),
                Transaction("t2", -0.39f, "", "Counterparty2", Date(), null)
        ))

        `when`(customerModel.get()).thenReturn(Observable.just(customer))

        return customer
    }

    private fun givenAnError(errorMessage: String) {
        `when`(customerModel.get()).thenReturn(Observable.error(IOException(errorMessage)))
    }

}
