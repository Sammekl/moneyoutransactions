package com.sammekleijn.moneyoutransactions.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sammekleijn.moneyoutransactions.R
import com.sammekleijn.moneyoutransactions.model.Transaction

class TransactionRecyclerViewAdapter(private var transactions: List<Transaction>, private val openListener: (Transaction?) -> Unit)
    : RecyclerView.Adapter<TransactionRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.showTransaction(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var transaction: Transaction? = null

        private val relativeLayout = view.findViewById<RelativeLayout>(R.id.endpointItemRelativeLayout)
        private val otherAccountTextView = view.findViewById<TextView>(R.id.otherAccountTextView)
        private val amountTextView = view.findViewById<TextView>(R.id.amountTextView)

        val context = view.context

        init {
            relativeLayout!!.setOnClickListener { openListener(transaction) }
        }

        fun showTransaction(transaction: Transaction) {
            this.transaction = transaction
            otherAccountTextView.text = transaction.otherAccount
            formatAmount(transaction.amount)
        }

        private fun formatAmount(amount: Float) {
            val incomingTransaction = amount > 0.0
            val colorResource: Int
            amountTextView.text = if (incomingTransaction) {
                colorResource = R.color.colorIncomingTransaction
                "+ € $amount"
            } else {
                colorResource = R.color.colorOutgoingTransaction
                "- € ${amount.unaryMinus()}"
            }

            amountTextView.setTextColor(ContextCompat.getColor(context, colorResource))
        }
    }
}