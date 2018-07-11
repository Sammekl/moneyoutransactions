package com.sammekleijn.moneyoutransactions.view

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.sammekleijn.moneyoutransactions.MainApplication
import com.sammekleijn.moneyoutransactions.injection.*
import org.junit.Before

abstract class BaseActivityTest {

    @Before
    fun setupApplicationComponent() {
        val testServiceModule = TestServiceModule()

        setupMocks(testServiceModule)

        val appcomponent: TestApplicationComponent by lazy {
            DaggerTestApplicationComponent
                    .builder()
                    .appModule(AppModule(MainApplication.instance))
                    .testServiceModule(testServiceModule)
                    .build()
        }

        ServiceLocator.applicationComponent = appcomponent

        doInject(appcomponent)
    }

    protected open fun doInject(testApplicationComponent: TestApplicationComponent) {

    }

    protected abstract fun setupMocks(testServiceModule: TestServiceModule)

    protected fun launchActivity(testRule: ActivityTestRule<*>) {
        testRule.launchActivity(Intent())
    }
}
