package com.cbr.behancesample.common


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
        setupNavigationGraph()
    }

    private fun setupNavigationGraph() {
        val navHost = supportFragmentManager.findFragmentById(navHostId()) as NavHostFragment
        val inflater = navHost.navController.navInflater
        val graph = inflater.inflate(getGraphResource())
        graph.setDefaultArguments(getExtras())
        navHost.navController.graph = graph
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

    open fun getExtras(): Bundle? = null

    @NavigationRes
    abstract fun getGraphResource(): Int

    abstract fun navHostId(): Int

    abstract fun getLayout(): Int

}