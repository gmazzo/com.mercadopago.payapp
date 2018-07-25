package com.mercadopago.apptest

import android.support.design.widget.TextInputLayout

var TextInputLayout.showError
    get() = error
    set(value) {
        error = value
        isErrorEnabled = !value.isNullOrBlank()
    }
