package com.sammekleijn.moneyoutransactions.injection.component

import com.sammekleijn.moneyoutransactions.injection.AppComponent
import com.sammekleijn.moneyoutransactions.injection.AppModule
import com.sammekleijn.moneyoutransactions.injection.module.TestServiceModule
import com.sammekleijn.moneyoutransactions.view.DashoardActivityTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, TestServiceModule::class])
interface TestAppComponent : AppComponent {

    fun inject(dashboardActivityTest: DashoardActivityTest)
}
