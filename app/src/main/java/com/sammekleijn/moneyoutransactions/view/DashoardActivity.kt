package com.sammekleijn.moneyoutransactions.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.model.Customer
import com.sammekleijn.moneyoutransactions.model.Transaction
import com.sammekleijn.moneyoutransactions.service.CustomerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dashoard.*
import java.util.ArrayList

class DashoardActivity : AppCompatActivity() {
    private val customerService: CustomerService = CustomerService()

    private var transactions: MutableList<Transaction> = ArrayList()

    lateinit var transactionRecyclerViewAdapter: TransactionRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashoard)

        setupAdapter()

        retrieveTransactions()

    }

    private fun retrieveTransactions() {
        CustomerService().getCustomer(this.applicationContext)
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
        title = "Account: ${customer.account}"
        transactions.clear()
        transactions.addAll(customer.transactions)
        transactionRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun open(transaction: Transaction?) {

    }
}
