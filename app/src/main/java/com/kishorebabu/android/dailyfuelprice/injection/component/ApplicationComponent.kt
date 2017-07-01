package com.kishorebabu.android.dailyfuelprice.injection.component

import com.kishorebabu.android.dailyfuelprice.data.DataManager
import com.kishorebabu.android.dailyfuelprice.data.remote.MvpStarterService
import com.kishorebabu.android.dailyfuelprice.injection.ApplicationContext
import com.kishorebabu.android.dailyfuelprice.injection.module.ApplicationModule
import android.app.Application
import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun mvpBoilerplateService(): MvpStarterService
}
