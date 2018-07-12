package com.sammekleijn.moneyoutransactions.injection

import com.sammekleijn.moneyoutransactions.view.DashboardActivityTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, TestServiceModule::class])
interface TestApplicationComponent : ApplicationComponent {

    fun inject(dashboardActivityTest: DashboardActivityTest)
}
