package com.sammekleijn.moneyoutransactions.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.extension.toEuro
import com.sammekleijn.moneyoutransactions.extension.toStringWithPrecision
import com.sammekleijn.moneyoutransactions.model.Customer
import com.sammekleijn.moneyoutransactions.model.Transaction
import kotlinx.android.synthetic.main.activity_transaction_detail.*

class TransactionDetailActivity : AppCompatActivity() {

    lateinit var transaction: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        transaction = intent.getParcelableExtra(TRANSACTION_EXTRA)
        show()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item);
    }

    private fun show() {
            title = getString(R.string.transaction_id, transaction.id)
            transactionCounterpartyTextView.text = transaction.otherAccount
            transactionAmountTextView.text = transaction.amount.toEuro()
            transactionDescriptionTextView.text = transaction.description
            transactionDateTextView.text = "${transaction.date}"
            balanceAfterTransactionTextView.text = if (transaction.balanceAfterTransaction != null) {
                getString(R.string.account_balance, transaction.balanceAfterTransaction!!.toStringWithPrecision(2))
            } else {
                getString(R.string.unknown)
            }

    }

    companion object {
        const val TRANSACTION_EXTRA = "TRANSACTION_KEY"

        fun createIntent(context: Context, transaction: Transaction): Intent {
            val intent = Intent(context, TransactionDetailActivity::class.java)
            intent.putExtra(TRANSACTION_EXTRA, transaction)

            return intent
        }
    }
}