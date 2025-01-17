package com.assesment.shared.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Activity.showToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}