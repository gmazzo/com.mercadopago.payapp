package com.mercadopago.apptest.payments.amount

import com.mercadopago.apptest.payments.models.Payment
import javax.inject.Inject

internal class PaymentAmountPresenter @Inject constructor(
        private val view: PaymentAmountContract.View) : PaymentAmountContract.Presenter {

    override fun onAmountEntered(amount: Int?) {
        if (amount != null) {
            view.showNextScreen(Payment(amount = amount))

        } else {
            view.showAmountError()
        }
    }

}
