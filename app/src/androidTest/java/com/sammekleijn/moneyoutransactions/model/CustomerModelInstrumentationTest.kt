package com.sammekleijn.moneyoutransactions.model

import android.support.test.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.junit.Test

class CustomerModelInstrumentationTest {

    @Test
    fun shouldSortTransactions() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val customer = CustomerModel(context).get().blockingFirst()

        assertEquals("trx1", customer.transactions[0].id)
        assertEquals("trx2", customer.transactions[1].id)
        assertEquals("trx3", customer.transactions[2].id)
        assertEquals("trx4", customer.transactions[3].id)
        assertEquals("trx5", customer.transactions[4].id)
    }
}