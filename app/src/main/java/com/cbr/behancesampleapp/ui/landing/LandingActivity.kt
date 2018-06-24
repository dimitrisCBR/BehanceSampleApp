package com.cbr.behancesampleapp.ui.landing

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpActivity
import com.cbr.behancesampleapp.ui.project.list.ProjectListFragment
import com.cbr.behancesampleapp.ui.user.list.UserListFragment
import kotlinx.android.synthetic.main.activity_landing.*


class LandingActivity : BaseMvpActivity() {

    override fun onActivityInject() {
        /* nothing for now */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_landing_users -> showUserFragment(false)
                R.id.menu_landing_collections -> showCollectionsFragment(false)
                R.id.menu_landing_projects -> showProjectsFragment(false)
                else -> showUserFragment(false)
            }
            true
        }
        showUserFragment(false)

    }

    private fun showUserFragment(addToBackStack: Boolean) {
        addFragment(UserListFragment(), addToBackStack)
    }

    private fun showProjectsFragment(addToBackStack: Boolean) {
        addFragment(ProjectListFragment(), addToBackStack)
    }

    private fun showCollectionsFragment(addToBackStack: Boolean) {
        //TODO
    }

    private fun addFragment(fragment: Fragment, addToBackStack: Boolean) {

        val existingFragment: Fragment? = supportFragmentManager.findFragmentByTag(fragment.tag)

        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, existingFragment ?: fragment)
        if (addToBackStack) {
            transaction.addToBackStack(fragment.tag)
        }
        transaction.commit()
    }
}
