package com.kishorebabu.android.dailyfuelprice.features.main

import com.kishorebabu.android.dailyfuelprice.features.base.MvpView

interface MainMvpView : MvpView {

    fun showPokemon(pokemon: List<String>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}