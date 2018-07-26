package com.mercadopago.apptest.payments.methods

import com.mercadopago.apptest.data.models.PaymentMethod
import com.mercadopago.apptest.payments.models.Payment

internal interface PaymentMethodContract {

    interface Presenter {

        fun onStartLoading()

        fun onMethodSelected(method: PaymentMethod)

    }

    interface View {

        fun showMethods(methods: List<PaymentMethod>)

        fun showNextScreen(payment: Payment)

        fun showError(error: Throwable)

    }

}
