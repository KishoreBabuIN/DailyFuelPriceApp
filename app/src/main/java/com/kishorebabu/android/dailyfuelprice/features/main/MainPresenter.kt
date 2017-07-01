package com.kishorebabu.android.dailyfuelprice.features.main

import com.kishorebabu.android.dailyfuelprice.data.DataManager
import com.kishorebabu.android.dailyfuelprice.injection.ConfigPersistent
import com.kishorebabu.android.dailyfuelprice.features.base.BasePresenter
import com.kishorebabu.android.dailyfuelprice.util.rx.scheduler.SchedulerUtils
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val mDataManager: DataManager) : BasePresenter<MainMvpView>() {

    override fun attachView(mvpView: MainMvpView) {
        super.attachView(mvpView)
    }

    fun getPokemon(limit: Int) {
        checkViewAttached()
        mvpView?.showProgress(true)
        mDataManager.getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain<List<String>>())
                .subscribe({ pokemons ->
                    mvpView?.showProgress(false)
                    mvpView?.showPokemon(pokemons)
                }) { throwable ->
                    mvpView?.showProgress(false)
                    mvpView?.showError(throwable)
                }
    }

}