package com.sammekleijn.moneyoutransactions.injection.module

import android.content.Context

import com.sammekleijn.moneyoutransactions.injection.ServiceModule
import com.sammekleijn.moneyoutransactions.service.CustomerService

import dagger.Module

@Module
open class TestServiceModule : ServiceModule() {

    var customerService: CustomerService? = null

    override fun buildCustomerService(context: Context): CustomerService {
        if (customerService == null) {
            customerService = super.buildCustomerService(context)
        }
        return customerService as CustomerService
    }

}
