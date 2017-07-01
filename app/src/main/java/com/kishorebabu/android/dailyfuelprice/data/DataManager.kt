package com.kishorebabu.android.dailyfuelprice.data

import com.kishorebabu.android.dailyfuelprice.data.model.CurrentFuelPrice
import com.kishorebabu.android.dailyfuelprice.data.model.Pokemon
import com.kishorebabu.android.dailyfuelprice.data.remote.FuelPriceService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val mFuelPriceService: FuelPriceService) {

    fun getCurrentPriceForCity(city: String): Single<CurrentFuelPrice> {
        return mFuelPriceService.getPriceForCity(city)
    }

    fun getPokemonList(limit: Int): Single<List<String>> {
        return mFuelPriceService.getPokemonList(limit)
                .toObservable()
                .flatMapIterable { (results) -> results }
                .map { (name) -> name }
                .toList()
    }

    fun getPokemon(name: String): Single<Pokemon> {
        return mFuelPriceService.getPokemon(name)
    }

}