package com.sammekleijn.moneyoutransactions.injection

import android.content.Context
import com.sammekleijn.moneyoutransactions.service.CustomerService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ServiceModule {

    @Provides
    @Singleton
    internal open fun customerService(context: Context): CustomerService {
        return buildCustomerService(context)
    }

    protected open fun buildCustomerService(context: Context): CustomerService {
        return CustomerService(context)
    }

}