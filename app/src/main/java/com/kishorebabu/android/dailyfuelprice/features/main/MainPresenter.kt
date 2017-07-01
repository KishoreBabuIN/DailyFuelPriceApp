package com.kishorebabu.android.dailyfuelprice.features.main

import com.kishorebabu.android.dailyfuelprice.data.DataManager
import com.kishorebabu.android.dailyfuelprice.data.model.CurrentFuelPrice
import com.kishorebabu.android.dailyfuelprice.features.base.BasePresenter
import com.kishorebabu.android.dailyfuelprice.injection.ConfigPersistent
import com.kishorebabu.android.dailyfuelprice.util.rx.scheduler.SchedulerUtils
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val mDataManager: DataManager) : BasePresenter<MainMvpView>() {

    override fun attachView(mvpView: MainMvpView) {
        super.attachView(mvpView)
    }

    fun getCurrentPriceForCity(city: String) {
        checkViewAttached()
        mDataManager.getCurrentPriceForCity(city)
                .compose(SchedulerUtils.ioToMain<CurrentFuelPrice>())
                .subscribe({
                    price ->
                    mvpView?.showPetrolPrice(price.petrol)
                    mvpView?.showDieselPrice(price.diesel)
                    Timber.v("Current Fuel Price: %s", price.toString())
                }) {
                    throwable ->
                    Timber.e(throwable, "Failed to get current price!")
                }
    }
}