package com.cbr.base.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.setVisible(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showKeyboard() {
    requestFocus()
    val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(this, 0)
}

fun View.inflater() = LayoutInflater.from(this.context)