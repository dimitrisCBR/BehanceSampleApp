package com.futureworkshops.core.extension

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Context.screenWidth(): Int {
    return displayMetrics().widthPixels
}

fun Context.screenHeight(): Int {
    return displayMetrics().heightPixels
}


fun Context.displayMetrics(): DisplayMetrics {
    val displayMetrics = DisplayMetrics()
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics
}

fun Context.statusBarHeight(): Int {
    val resId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resId > 0) this.resources.getDimensionPixelSize(resId) else -1
}

fun Context.getToolbarHeight(): Int {
    val tv = TypedValue()
    var toolbarHeight = 0
    if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }

    return toolbarHeight
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}