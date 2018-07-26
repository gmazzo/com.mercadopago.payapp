package com.mercadopago.apptest

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(context)
                .inflate(layoutId, this, attachToRoot)!!

var TextInputLayout.showError
    get() = error
    set(value) {
        error = value
        isErrorEnabled = !value.isNullOrBlank()
    }

fun Fragment.replaceWith(fragment: Fragment, tag: String? = null) =
        fragmentManager!!.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .replace(id, fragment, tag)
                .addToBackStack(null)
                .commit()

fun CharSequence.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Throwable.toast(context: Context) {
    printStackTrace()

    localizedMessage.toast(context, Toast.LENGTH_LONG)
}
