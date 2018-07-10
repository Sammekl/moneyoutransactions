package com.sammekleijn.moneyoutransactions.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.extension.toEuro
import com.sammekleijn.moneyoutransactions.extension.toStringWithPrecision
import com.sammekleijn.moneyoutransactions.model.Transaction
import kotlinx.android.synthetic.main.activity_transaction_detail.*

class TransactionDetailActivity : AppCompatActivity() {

    lateinit var transaction: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail)

        transaction = intent.getParcelableExtra(TRANSACTION_EXTRA)
        show(transaction)

    }

    private fun show(transaction: Transaction?) {
        transaction?.let {
            transactionIdTextView.text = it.id
            transactionCounterpartyTextView.text = it.otherAccount
            transactionAmountTextView.text = it.amount.toEuro()
            transactionDescriptionTextView.text = it.description
            transactionDateTextView.text = "${it.date}"
//            balanceAfterTransactionTextView.text = it.otherAccount
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
