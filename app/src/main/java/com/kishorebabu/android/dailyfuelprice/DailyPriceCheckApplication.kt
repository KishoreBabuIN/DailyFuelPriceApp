package com.kishorebabu.android.dailyfuelprice

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.kishorebabu.android.dailyfuelprice.injection.component.ApplicationComponent
import com.kishorebabu.android.dailyfuelprice.injection.module.ApplicationModule
import com.kishorebabu.android.dailyfuelprice.util.CrashReportingTree
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class DailyPriceCheckApplication : MultiDexApplication() {

    internal var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
        } else {
            val crashReportingTree = CrashReportingTree()
            Timber.plant(crashReportingTree)
        }
    }

    // Needed to replace the component with a test specific one
    var component: ApplicationComponent
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
            }
            return mApplicationComponent as ApplicationComponent
        }
        set(applicationComponent) {
            mApplicationComponent = applicationComponent
        }

    companion object {

        operator fun get(context: Context): DailyPriceCheckApplication {
            return context.applicationContext as DailyPriceCheckApplication
        }
    }
}

