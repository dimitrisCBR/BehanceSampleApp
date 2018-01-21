package com.cbr.behancesampleapp.ui.common.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cbr.behancesampleapp.BehanceSampleApp
import com.cbr.behancesampleapp.dagger.AppComponent

/** Created by Dimitrios on 8/26/2017. */
abstract class BaseMvpActivity : AppCompatActivity(), MvpView {
    
    private var mPresenter: MvpPresenter<*>? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }
    
    protected abstract fun onActivityInject()
    
    override fun setPresenter(presenter: MvpPresenter<*>) {
        mPresenter = presenter
    }
    
    fun appComponent(): AppComponent = BehanceSampleApp.appComponent
    
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }
    
    override fun onResume() {
        super.onResume()
        mPresenter?.subscribe()
    }
    
    override fun onPause() {
        super.onPause()
        mPresenter?.unsubscribe()
    }
}
