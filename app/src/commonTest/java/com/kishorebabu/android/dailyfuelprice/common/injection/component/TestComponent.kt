package com.kishorebabu.android.dailyfuelprice.common.injection.component

import com.kishorebabu.android.dailyfuelprice.common.injection.module.ApplicationTestModule
import com.kishorebabu.android.dailyfuelprice.injection.component.ApplicationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent