package com.mercadopago.payapp.payments.methods

import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.payments.models.Payment

internal interface PaymentMethodContract {

    interface Presenter {

        fun onStart()

        fun onStop()

        fun onMethodSelected(method: PaymentMethod)

    }

    interface View {

        fun showMethods(methods: List<PaymentMethod>)

        fun showNextScreen(payment: Payment)

        fun showError(error: Throwable)

    }

}
