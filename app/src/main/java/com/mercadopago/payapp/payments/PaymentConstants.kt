package com.mercadopago.payapp.payments

import android.os.Bundle
import com.mercadopago.payapp.payments.models.Payment

const val ARG_PAYMENT = "payment"

fun Bundle.getPayment() =
        getParcelable(com.mercadopago.payapp.payments.ARG_PAYMENT) as Payment
