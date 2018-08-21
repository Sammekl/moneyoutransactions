package com.sammekleijn.moneyoutransactions.injection

import android.content.Context
import com.sammekleijn.moneyoutransactions.model.CustomerModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ServiceModule {

    @Provides
    @Singleton
    internal open fun customerModel(context: Context): CustomerModel {
        return buildCustomerModel(context)
    }

    protected open fun buildCustomerModel(context: Context): CustomerModel {
        return CustomerModel(context)
    }

}