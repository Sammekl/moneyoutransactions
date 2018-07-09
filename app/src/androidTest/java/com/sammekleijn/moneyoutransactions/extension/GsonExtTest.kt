package com.sammekleijn.moneyoutransactions.extension

import android.support.test.InstrumentationRegistry
import com.sammekleijn.moneyoutransactions.model.Customer
import junit.framework.Assert.*
import org.junit.Test


class GsonExtTest {

    @Test
    fun readsCustomerFromJsonFile() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream = context.assets.open("transactions.json")

        val customer = inputStream.fromJson(Customer::class.java)

        assertNotNull(customer)
        assertEquals("NL30MOYO0001234567", customer.account)
        assertEquals(5, customer.transactions.size)
    }
}
