package com.cbr.behance.ui.user.details

import androidx.fragment.app.Fragment


class UserDetailsFragment : Fragment() {


    private val userId : Long
        get() = UserDetailsFragmentArgs.fromBundle(arguments).userId


}