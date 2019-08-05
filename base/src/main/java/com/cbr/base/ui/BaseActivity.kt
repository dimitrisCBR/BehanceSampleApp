package com.cbr.base.ui


import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment


abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun setupToolbar(toolbar: Toolbar, showHomeAsUp: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(showHomeAsUp)
            it.setDisplayShowHomeEnabled(false)
        }
    }

    open fun getExtras(): Bundle? = intent.extras

    abstract fun getLayout(): Int

}