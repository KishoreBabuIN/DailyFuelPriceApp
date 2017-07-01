package com.kishorebabu.android.dailyfuelprice.features.main

import com.kishorebabu.android.dailyfuelprice.features.base.MvpView

interface MainMvpView : MvpView {

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)
    fun showPetrolPrice(petrol: Double)
    fun showDieselPrice(diesel: Double)

}