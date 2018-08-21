package com.sammekleijn.moneyoutransactions.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sammekleijn.moneyoutransactions.databinding.TransactionListItemBinding
import com.sammekleijn.moneyoutransactions.domain.Transaction

class TransactionRecyclerViewAdapter(private var transactions: MutableList<Transaction>, private val openListener: OnTransactionClickListener)
    : RecyclerView.Adapter<TransactionRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TransactionListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(transactions[position], openListener)


    override fun getItemCount(): Int {
        return transactions.size
    }

    interface OnTransactionClickListener {
        fun onTransactionClick(transaction: Transaction)
    }

    fun set(arrayList: MutableList<Transaction>) {
        transactions = arrayList
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: TransactionListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction, listener: OnTransactionClickListener?) {
            binding.transaction = transaction
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onTransactionClick(transaction) }
            }

            binding.executePendingBindings()
        }
    }
}