package com.mercadopago.payapp.payments.methods

import com.mercadopago.payapp.data.models.PaymentMethod

internal interface PaymentMethodContract {

    interface Presenter {

        fun onStart()

        fun onStop()

        fun onMethodSelected(method: PaymentMethod)

    }

    interface View {

        fun showMethods(methods: List<PaymentMethod>)

        fun showNextScreen()

        fun showError(error: Throwable)

    }

}
