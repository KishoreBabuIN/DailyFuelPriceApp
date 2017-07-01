package com.kishorebabu.android.dailyfuelprice

import com.kishorebabu.android.dailyfuelprice.common.TestDataFactory
import com.kishorebabu.android.dailyfuelprice.data.DataManager
import com.kishorebabu.android.dailyfuelprice.features.main.MainMvpView
import com.kishorebabu.android.dailyfuelprice.features.main.MainPresenter
import com.kishorebabu.android.dailyfuelprice.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock lateinit var mMockMainMvpView: MainMvpView
    @Mock lateinit var mMockDataManager: DataManager
    private var mMainPresenter: MainPresenter? = null

    @JvmField
    @Rule
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        mMainPresenter = MainPresenter(mMockDataManager)
        mMainPresenter?.attachView(mMockMainMvpView)
    }

    @After
    fun tearDown() {
        mMainPresenter?.detachView()
    }

}