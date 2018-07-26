package com.mercadopago.apptest.payments.amount

import com.mercadopago.apptest.payments.models.Payment

internal interface PaymentAmountContract {

    interface Presenter {

        fun onAmountEntered(amount: Float?)

    }

    interface View {

        fun showAmountError()

        fun showNextScreen(payment: Payment)

    }

}