package com.cbr.behance.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cbr.base.ui.BaseActivity
import com.cbr.behance.R
import kotlinx.android.synthetic.main.activity_home.*


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

    override fun navHostId(): Int = R.id.navigationHost

    override fun onBackPressed() {
        if (!onSupportNavigateUp()) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

}