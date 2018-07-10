package com.sammekleijn.moneyoutransactions

import android.app.Application
import com.sammekleijn.moneyoutransactions.injection.AppComponent
import com.sammekleijn.moneyoutransactions.injection.AppModule
import com.sammekleijn.moneyoutransactions.injection.DaggerAppComponent
import com.sammekleijn.moneyoutransactions.injection.ServiceLocator

class MainApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)

        ServiceLocator.applicationComponent = component
        ServiceLocator.application = this
    }
}