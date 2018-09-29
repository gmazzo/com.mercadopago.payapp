package com.mercadopago.payapp.payments.amount

internal interface PaymentAmountContract {

    interface Presenter {

        fun onStart()

        fun onAmountEntered(amount: Float?)

    }

    interface View {

        fun showAmountError()

        fun showNextScreen()

    }

}
