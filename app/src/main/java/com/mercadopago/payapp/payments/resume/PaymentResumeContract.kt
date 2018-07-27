package com.mercadopago.payapp.payments.resume

import com.mercadopago.payapp.payments.models.Payment

internal interface PaymentResumeContract {

    interface Presenter {

        fun onStart()

    }

    interface View {

        fun showPayment(payment: Payment)

    }

}
