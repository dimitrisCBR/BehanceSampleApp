package com.futureworkshops.domain.extension

import androidx.fragment.app.Fragment


fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}