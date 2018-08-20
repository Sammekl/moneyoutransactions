package com.sammekleijn.moneyoutransactions.domain

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class CustomerTest {

    lateinit var customer: Customer

    @Before
    fun setup() {
        val transaction1 = Transaction("1", 100.20f, "Transaction1", "Counterparty1", Date(1531289191), null)
        val transaction2 = Transaction("2", 89.00f, "Transaction2", "Counterparty2", Date(1431289191), null)
        val transaction3 = Transaction("3", -99.99f, "Transaction3", "Counterparty3", Date(1331289191), null)
        val transaction4 = Transaction("4", -0.40f, "Transaction4", "Counterparty1", Date(1231289191), null)
        val transaction5 = Transaction("5", 50.30f, "Transaction5", "Counterparty2", Date(1131289191), null)

        val transactions = mutableListOf(transaction1, transaction2, transaction3, transaction4, transaction5)

        customer = Customer("Account1", 400f, transactions)
    }

    @Test
    fun testBalanceAtDate() {
        assertEquals(customer.balance, customer.getBalanceAt(Date(1631289191)))
        assertEquals(260.89f, customer.getBalanceAt(Date(1031289191)), 2f)
        assertEquals(210.80f, customer.getBalanceAt(Date(1331289191)), 2f)
    }
}