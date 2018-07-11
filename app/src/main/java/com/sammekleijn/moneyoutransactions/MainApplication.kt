package com.sammekleijn.moneyoutransactions

import android.app.Application
import com.sammekleijn.moneyoutransactions.injection.*

class MainApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component.inject(this)

        ServiceLocator.applicationComponent = component
        ServiceLocator.application = this
    }

    companion object {
        lateinit var instance: MainApplication
    }
}