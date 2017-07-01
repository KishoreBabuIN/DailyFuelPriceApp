package com.kishorebabu.android.dailyfuelprice.injection.module

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import com.kishorebabu.android.dailyfuelprice.data.remote.FuelPriceService
import com.kishorebabu.android.dailyfuelprice.data.remote.FuelPriceServiceFactory
import com.kishorebabu.android.dailyfuelprice.injection.ApplicationContext

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideMvpStarterService(): FuelPriceService {
        return FuelPriceServiceFactory.makeStarterService()
    }
}
