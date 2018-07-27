package com.mercadopago.payapp

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v7.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(context)
                .inflate(layoutId, this, attachToRoot)!!

fun LayoutInflater.withStyle(@StyleRes styleId: Int) =
        cloneInContext(ContextThemeWrapper(context, styleId))

var TextView.textOrHide: CharSequence?
    get() = text
    set(value) {
        text = value
        isVisible = !value.isNullOrBlank()
    }

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

fun Throwable.toast(context: Context?) {
    printStackTrace()

    if (context != null) {
        localizedMessage.toast(context, Toast.LENGTH_LONG)
    }
}
