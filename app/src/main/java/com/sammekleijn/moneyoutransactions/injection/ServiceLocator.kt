package com.sammekleijn.moneyoutransactions.injection

import android.app.Application

object ServiceLocator {

    var applicationComponent: AppComponent? = null
    var application: Application? = null
}
