package com.kishorebabu.android.dailyfuelprice.features.main

import android.location.Location
import com.kishorebabu.android.dailyfuelprice.features.base.MvpView

interface MainMvpView : MvpView {

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)
    fun showPetrolPrice(petrol: Double)
    fun showDieselPrice(diesel: Double)
    fun fetchLastKnownLocation()
    fun getCityForLocation(location: Location?)
    fun showCityName(currentCity: String?, adminArea: String, countryCode: String)

}