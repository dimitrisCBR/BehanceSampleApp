package com.cbr.behancesampleapp.ui.common.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.cbr.behancesampleapp.BehanceSampleApp
import com.cbr.behancesampleapp.domain.dagger.AppComponent

/** Created by Dimitrios on 8/26/2017. */
abstract class BaseMvpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    protected abstract fun onActivityInject()

    fun appComponent(): AppComponent = BehanceSampleApp.appComponent

}
