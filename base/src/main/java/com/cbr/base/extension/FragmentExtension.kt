package com.cbr.base.extension

import androidx.fragment.app.Fragment
import com.cbr.base.extension.hideKeyboard


fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}