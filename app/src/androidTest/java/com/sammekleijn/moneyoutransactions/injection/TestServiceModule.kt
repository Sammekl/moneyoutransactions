package com.sammekleijn.moneyoutransactions.injection

import android.content.Context
import com.sammekleijn.moneyoutransactions.model.CustomerModel
import dagger.Module

@Module
open class TestServiceModule : ServiceModule() {

    var customerModel: CustomerModel? = null

    override fun buildCustomerModel(context: Context): CustomerModel {
        if (customerModel == null) {
            customerModel = super.buildCustomerModel(context)
        }
        return customerModel as CustomerModel
    }
}
