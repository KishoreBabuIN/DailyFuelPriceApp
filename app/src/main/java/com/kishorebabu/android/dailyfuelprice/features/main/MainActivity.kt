package com.kishorebabu.android.dailyfuelprice.features.main

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import com.kishorebabu.android.dailyfuelprice.R
import com.kishorebabu.android.dailyfuelprice.features.base.BaseActivity
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import com.tbruyelle.rxpermissions.RxPermissions
import pl.charmas.android.reactivelocation.ReactiveLocationProvider
import timber.log.Timber
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMvpView {
    @Inject lateinit var mMainPresenter: MainPresenter

    @BindView(R.id.progress) @JvmField var mProgress: ProgressBar? = null
    @BindView(R.id.petrol_price_view) @JvmField var mPetrolPriceView: TickerView? = null
    @BindView(R.id.diesel_price_view) @JvmField var mDieselPriceView: TickerView? = null
    @BindView(R.id.toolbar) @JvmField var mToolbar: Toolbar? = null
    private var locationProvider: ReactiveLocationProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mMainPresenter.attachView(this)

        val rxPermission = RxPermissions(this)

        rxPermission
                .request(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ granted ->
                    if (granted) { // Always true pre-M
                        mMainPresenter.onLocationPermissionGranted()
                    } else {
                        // Oups permission denied
                    }
                })
    }

    override fun showCityName(currentCity: String?, adminArea: String, countryCode: String) {
        mToolbar?.title = String.format("%s, %s, %s", currentCity, adminArea, countryCode)
        setSupportActionBar(mToolbar)
    }

    @SuppressLint("MissingPermission")
    override fun fetchLastKnownLocation() {
        locationProvider = ReactiveLocationProvider(this)
        locationProvider?.lastKnownLocation
                ?.subscribe {
                    location ->
                    mMainPresenter.onLocationReceived(location)
                }
    }

    override fun showPetrolPrice(petrol: Double) {
        mPetrolPriceView?.setCharacterList(TickerUtils.getDefaultNumberList())
        mPetrolPriceView?.setText("₹".plus(petrol.toString()))
    }

    override fun showDieselPrice(diesel: Double) {
        mDieselPriceView?.setCharacterList(TickerUtils.getDefaultNumberList())
        mDieselPriceView?.setText("₹".plus(diesel.toString()))
    }

    override val layout: Int
        get() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mMainPresenter.detachView()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            mProgress?.visibility = View.VISIBLE
        } else {
            mProgress?.visibility = View.GONE
        }
    }

    override fun showError(error: Throwable) {
        Timber.e(error, "There was an error retrieving the pokemon")
    }

    override fun getCityForLocation(location: Location?) {
        locationProvider?.getReverseGeocodeObservable(location!!.latitude, location!!.longitude, 1)
                ?.subscribe({ addresses ->
                    mMainPresenter.onAddressReceived(addresses)
                })
    }
}