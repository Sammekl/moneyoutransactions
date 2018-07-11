package com.sammekleijn.moneyoutransactions.injection

import android.app.Application

object ServiceLocator {

    var applicationComponent: ApplicationComponent? = null
    var application: Application? = null
}
