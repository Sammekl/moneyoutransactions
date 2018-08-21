package com.sammekleijn.moneyoutransactions.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.databinding.ActivityTransactionDetailBinding
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.domain.Transaction
import com.sammekleijn.moneyoutransactions.viewmodel.TransactionDetailModel

class TransactionDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityTransactionDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_detail)
        val viewModel = ViewModelProviders.of(this).get(TransactionDetailModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.show(intent.getParcelableExtra(TRANSACTION_EXTRA))

        viewModel.transaction.observe(this,
                Observer<Transaction> {
                    it?.let {
                        binding.transaction = it
                    }
                }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            closeActivity()
        }
        return super.onOptionsItemSelected(item);
    }

    override fun onBackPressed() {
        closeActivity()
    }

    private fun closeActivity() {
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
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
