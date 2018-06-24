package com.cbr.behancesampleapp.ui.common.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cbr.behancesampleapp.BehanceSampleApp

/** Created by Dimitrios on 8/26/2017. */
abstract class BaseMvpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFragmentInject()
    }

    protected abstract fun onFragmentInject()

    fun appComponent() = BehanceSampleApp.appComponent
}
