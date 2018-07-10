package com.sammekleijn.moneyoutransactions.view

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.model.Transaction
import org.junit.Rule
import org.junit.Test
import java.util.*

class TransactionDetailActivityTest {

    @Rule
    @JvmField
    val transactionDetailActivity = ActivityTestRule(TransactionDetailActivity::class.java, true, false)

    @Test
    fun showsTransactionWhenActivityIsLaunched() {
        val transaction = Transaction("123", 671.23f, "This is a test", "Belastingdienst", Date())

        launchActivityWith(transaction)

        verifyTransactionIsShown(transaction)

    }

    private fun verifyTransactionIsShown(transaction: Transaction) {
        onView(withId(R.id.transactionIdTextView)).check(matches(withText(transaction.id)))

        val transactionAmount = if (transaction.amount > 0) {
            "+ €${transaction.amount}"
        } else {
            "- €${transaction.amount}"
        }

        onView(withId(R.id.transactionAmountTextView)).check(matches(withText(transactionAmount)))
        onView(withId(R.id.transactionCounterpartyTextView)).check(matches(withText(transaction.otherAccount)))
        onView(withId(R.id.transactionDateTextView)).check(matches(withText(transaction.date.toString())))
        onView(withId(R.id.transactionDescriptionTextView)).check(matches(withText(transaction.description)))

    }

    private fun launchActivityWith(transaction: Transaction) {
        val intent = Intent()
        intent.putExtra(TransactionDetailActivity.TRANSACTION_EXTRA, transaction)
        transactionDetailActivity.launchActivity(intent)
    }
}