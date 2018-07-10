package com.sammekleijn.moneyoutransactions.service

import android.support.test.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.junit.Test

class CustomerServiceTest {

    @Test
    fun shouldSortTransactions() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val customer = CustomerService(context).getCustomer().blockingGet()

        assertEquals("trx1", customer.transactions[0].id)
        assertEquals("trx2", customer.transactions[1].id)
        assertEquals("trx3", customer.transactions[2].id)
        assertEquals("trx4", customer.transactions[3].id)
        assertEquals("trx5", customer.transactions[4].id)
    }
}