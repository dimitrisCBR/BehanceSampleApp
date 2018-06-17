package com.cbr.behancesampleapp.ui.common.mvp

import android.app.Fragment
import android.os.Bundle
import com.cbr.behancesampleapp.BehanceSampleApp

/** Created by Dimitrios on 8/26/2017. */
abstract class BaseMvpFragment : Fragment(), MvpView {
    
    private var mPresenter: MvpPresenter<*>? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFragmentInject()
    }
    
    protected abstract fun onFragmentInject()
    
    override fun setPresenter(presenter: MvpPresenter<*>) {
        this.mPresenter = presenter
    }
    
    fun appComponent() = BehanceSampleApp.appComponent
    
    override fun onResume() {
        super.onResume()
        mPresenter?.subscribe()
    }
    
    override fun onPause() {
        super.onPause()
        mPresenter?.unsubscribe()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }
}
