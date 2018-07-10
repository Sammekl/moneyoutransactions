package com.sammekleijn.moneyoutransactions.injection

import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.view.DashoardActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface AppComponent {

    fun inject(app: MainApplication)

    fun inject(dashoardActivity: DashoardActivity)
}