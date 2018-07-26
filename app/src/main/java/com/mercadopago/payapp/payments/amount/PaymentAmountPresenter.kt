package com.mercadopago.payapp.payments.amount

import com.mercadopago.payapp.payments.models.Payment
import javax.inject.Inject

internal class PaymentAmountPresenter @Inject constructor(
        private val view: PaymentAmountContract.View) : PaymentAmountContract.Presenter {

    override fun onAmountEntered(amount: Float?) {
        if (amount != null) {
            view.showNextScreen(Payment(amount = amount))

        } else {
            view.showAmountError()
        }
    }

}
