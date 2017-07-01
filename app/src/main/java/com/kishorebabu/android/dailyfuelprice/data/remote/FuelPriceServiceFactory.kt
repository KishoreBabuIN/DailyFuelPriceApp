package com.kishorebabu.android.dailyfuelprice.data.remote

import com.kishorebabu.android.dailyfuelprice.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Provide "make" methods to create instances of [FuelPriceService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object FuelPriceServiceFactory {

    fun makeStarterService(): FuelPriceService {
        return makeMvpStarterService(makeGson())
    }

    private fun makeMvpStarterService(gson: Gson): FuelPriceService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.FUEL_PRICE_INDIA_BASE_URL)
                .client(makeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(FuelPriceService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {

        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
            httpClientBuilder.addNetworkInterceptor(StethoInterceptor())
        }

        return httpClientBuilder.build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}
