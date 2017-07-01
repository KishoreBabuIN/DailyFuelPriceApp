package com.kishorebabu.android.dailyfuelprice.injection.component

import com.kishorebabu.android.dailyfuelprice.injection.PerFragment
import com.kishorebabu.android.dailyfuelprice.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent