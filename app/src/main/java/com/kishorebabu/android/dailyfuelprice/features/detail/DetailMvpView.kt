package com.kishorebabu.android.dailyfuelprice.features.detail

import com.kishorebabu.android.dailyfuelprice.data.model.Pokemon
import com.kishorebabu.android.dailyfuelprice.data.model.Statistic
import com.kishorebabu.android.dailyfuelprice.features.base.MvpView

interface DetailMvpView : MvpView {

    fun showPokemon(pokemon: Pokemon)

    fun showStat(statistic: Statistic)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}