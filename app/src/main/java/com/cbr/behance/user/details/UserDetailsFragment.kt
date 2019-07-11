package com.cbr.behance.user.details

import androidx.fragment.app.Fragment


class UserDetailsFragment : Fragment() {

    private val userId : Long
        get() = UserDetailsFragmentArgs.fromBundle(arguments).userId

}