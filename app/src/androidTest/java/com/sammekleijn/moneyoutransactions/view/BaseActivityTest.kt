package com.sammekleijn.moneyoutransactions.view

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.sammekleijn.moneyoutransactions.injection.ServiceLocator
import com.sammekleijn.moneyoutransactions.injection.component.TestAppComponent
import com.sammekleijn.moneyoutransactions.injection.module.TestServiceModule
import org.junit.Before

abstract class BaseActivityTest {

    @Before
    fun setupApplicationComponent() {
        val testServiceModule = TestServiceModule()

        setupMocks(testServiceModule)

//        val appcomponent: TestAppComponent by lazy {
//            DaggerTestAppComponent
//                    .builder()
//                    .build()
//        }
//
//        ServiceLocator.applicationComponent = appcomponent
//
//        doInject(appcomponent)
    }

    protected fun doInject(appComponent: TestAppComponent) {

    }

    protected abstract fun setupMocks(testServiceModule: TestServiceModule)

    protected fun launchActivity(testRule: ActivityTestRule<*>) {
        testRule.launchActivity(Intent())
    }
}
