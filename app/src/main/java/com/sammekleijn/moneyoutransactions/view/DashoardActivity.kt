package com.sammekleijn.moneyoutransactions.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.injection.ServiceLocator
import com.sammekleijn.moneyoutransactions.model.Customer
import com.sammekleijn.moneyoutransactions.model.Transaction
import com.sammekleijn.moneyoutransactions.service.CustomerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dashoard.*
import java.util.*
import javax.inject.Inject

class DashoardActivity : AppCompatActivity() {

    @Inject
    lateinit var customerService: CustomerService

    private var transactions: MutableList<Transaction> = ArrayList()

    lateinit var transactionRecyclerViewAdapter: TransactionRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServiceLocator.applicationComponent?.inject(this)
        setContentView(R.layout.activity_dashoard)

        setupAdapter()

        retrieveTransactions()

    }

    private fun retrieveTransactions() {
        customerService.getCustomer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ customer ->
                    show(customer)
                }, { error ->
                    Snackbar.make(coordinatorLayout, "Something when wrong while retrieving your account", Snackbar.LENGTH_LONG)
                })
    }

    private fun setupAdapter() {
        transactionRecyclerViewAdapter = TransactionRecyclerViewAdapter(transactions) { open(it) }

        val linearLayoutManager = LinearLayoutManager(transactionsRecyclerView.context)
        transactionsRecyclerView.layoutManager = linearLayoutManager

        val dividerLine = DividerItemDecoration(transactionsRecyclerView.context, linearLayoutManager.orientation)

        transactionsRecyclerView.addItemDecoration(dividerLine)
        transactionsRecyclerView.adapter = transactionRecyclerViewAdapter
    }

    private fun show(customer: Customer) {
        accountNumberTextView.text = customer.account
        accountBalanceTextView.text = getString(R.string.account_balance, "%.2f".format(customer.balance))

        transactions.clear()
        transactions.addAll(customer.transactions)
        transactionRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun open(transaction: Transaction?) {

    }
}
