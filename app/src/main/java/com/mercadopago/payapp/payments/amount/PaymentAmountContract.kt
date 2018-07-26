package com.mercadopago.payapp.payments.amount

import com.mercadopago.payapp.payments.models.Payment

internal interface PaymentAmountContract {

    interface Presenter {

        fun onStart()

        fun onAmountEntered(amount: Float?)

    }

    interface View {

        fun showAmountError()

        fun showNextScreen(payment: Payment)

    }

}
