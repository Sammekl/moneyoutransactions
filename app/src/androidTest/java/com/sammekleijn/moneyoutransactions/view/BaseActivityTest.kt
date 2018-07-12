package com.sammekleijn.moneyoutransactions.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
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

    fun verifySnackBarIsShown(message: String) {
        Thread.sleep(500)

        onView(withText(message)).check(matches(isDisplayed()))
    }
}
