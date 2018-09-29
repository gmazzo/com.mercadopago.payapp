package com.mercadopago.payapp.payments.banks

import com.mercadopago.payapp.data.models.PaymentBank

internal interface PaymentBankContract {

    interface Presenter {

        fun onStart()

        fun onStop()

        fun onBankSelected(bank: PaymentBank)

    }

    interface View {

        fun showBanks(banks: List<PaymentBank>)

        fun showNextScreen()

        fun showError(error: Throwable)

    }

}
