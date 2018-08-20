package com.sammekleijn.moneyoutransactions.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.databinding.ActivityDashboardBinding
import com.sammekleijn.moneyoutransactions.domain.Customer
import com.sammekleijn.moneyoutransactions.domain.Transaction
import com.sammekleijn.moneyoutransactions.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), TransactionRecyclerViewAdapter.OnTransactionClickListener {
    val transactionRecyclerViewAdapter = TransactionRecyclerViewAdapter(arrayListOf(), this)

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        val viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.transactionsRv.layoutManager = LinearLayoutManager(this)
        binding.transactionsRv.adapter = transactionRecyclerViewAdapter

        viewModel.customer.observe(this,
                Observer<Customer> {
                    it?.let {
                        binding.customer = it
                        transactionRecyclerViewAdapter.set(it.transactions)
                    }
                }
        )

        viewModel.loadCustomer()
    }

    override fun onTransactionClick(transaction: Transaction) {
        val intent = TransactionDetailActivity.createIntent(this, transaction)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}
