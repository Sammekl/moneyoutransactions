package com.sammekleijn.moneyoutransactions.injection

import android.content.Context
import com.sammekleijn.moneyoutransactions.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: MainApplication) {

    @Provides
    @Singleton
    fun provideApp() = application

    @Provides
    fun providesContext(): Context {
        return application.applicationContext
    }
}