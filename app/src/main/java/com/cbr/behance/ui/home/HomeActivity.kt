package com.cbr.behance.ui.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cbr.behance.R
import com.cbr.behance.common.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_appbar.*


class HomeActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment
        navController = navHost.navController
        navController.addOnNavigatedListener { _, destination ->
            supportActionBar?.title = destination.label
        }

        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    override fun getLayout(): Int = R.layout.activity_home

    override fun getGraphResource(): Int = R.navigation.nav_graph

    override fun navHostId(): Int =  R.id.navigationHost

    override fun onBackPressed() {
        if (!onSupportNavigateUp()) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

}