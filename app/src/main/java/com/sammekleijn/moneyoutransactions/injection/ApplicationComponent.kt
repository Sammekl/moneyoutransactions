package com.sammekleijn.moneyoutransactions.injection

import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.viewmodel.DashboardViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface ApplicationComponent {

    fun inject(app: MainApplication)

    fun inject(dashboardViewModel: DashboardViewModel)
}