package com.sammekleijn.moneyoutransactions.injection

import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.view.DashboardActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface ApplicationComponent {

    fun inject(app: MainApplication)

    fun inject(dashboardActivity: DashboardActivity)
}