package com.kishorebabu.android.dailyfuelprice

import com.kishorebabu.android.dailyfuelprice.common.TestDataFactory
import com.kishorebabu.android.dailyfuelprice.data.DataManager
import com.kishorebabu.android.dailyfuelprice.data.model.PokemonListResponse
import com.kishorebabu.android.dailyfuelprice.data.remote.FuelPriceService
import com.kishorebabu.android.dailyfuelprice.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    @Rule @JvmField val mOverrideSchedulersRule = RxSchedulersOverrideRule()
    @Mock lateinit var mMockFuelPriceService: FuelPriceService

    private var mDataManager: DataManager? = null

    @Before
    fun setUp() {
        mDataManager = DataManager(mMockFuelPriceService)
    }

    @Test
    fun getPokemonListCompletesAndEmitsPokemonList() {
        val namedResourceList = TestDataFactory.makeNamedResourceList(5)
        val pokemonListResponse = PokemonListResponse(namedResourceList)

        `when`(mMockFuelPriceService.getPokemonList(anyInt()))
                .thenReturn(Single.just(pokemonListResponse))

        mDataManager?.getPokemonList(10)
                ?.test()
                ?.assertComplete()
                ?.assertValue(TestDataFactory.makePokemonNameList(namedResourceList))
    }

    @Test
    fun getPokemonCompletesAndEmitsPokemon() {
        val name = "charmander"
        val pokemon = TestDataFactory.makePokemon(name)
        `when`(mMockFuelPriceService.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon))

        mDataManager?.getPokemon(name)
                ?.test()
                ?.assertComplete()
                ?.assertValue(pokemon)
    }
}
