package com.cbr.base.ui

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment

abstract class NavActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigationGraph()
    }

    private fun setupNavigationGraph() {
        val navHost = supportFragmentManager.findFragmentById(navHostId()) as NavHostFragment
        val inflater = navHost.navController.navInflater
        val graph = inflater.inflate(getGraphResource())
        graph.setDefaultArguments(getExtras())
        navHost.navController.graph = graph
    }

    @NavigationRes
    abstract fun getGraphResource(): Int

    abstract fun navHostId(): Int
}