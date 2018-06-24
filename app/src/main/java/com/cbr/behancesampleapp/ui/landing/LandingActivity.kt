package com.cbr.behancesampleapp.ui.landing

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpActivity
import com.cbr.behancesampleapp.ui.user.list.UserListFragment
import kotlinx.android.synthetic.main.activity_landing.*


class LandingActivity : BaseMvpActivity() {

    override fun onActivityInject() {
        /* nothing for now */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        addFragment(UserListFragment(), true)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_landing_users -> addFragment(UserListFragment(), true)
                R.id.menu_landing_collections -> showCollectionsFragment()
                R.id.menu_landing_projects -> showProjectsFragment()
                else -> showProjectsFragment()
            }
            true
        }
    }

    private fun showProjectsFragment() {
        //TODO
    }

    private fun showCollectionsFragment() {
        //TODO
    }

    private fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, UserListFragment())
        if (addToBackStack) {
            transaction.addToBackStack(fragment.tag)
        }
        transaction.commit()
    }
}
