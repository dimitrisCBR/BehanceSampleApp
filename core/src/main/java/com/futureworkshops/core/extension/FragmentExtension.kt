package com.futureworkshops.core.extension

import androidx.fragment.app.Fragment


fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}