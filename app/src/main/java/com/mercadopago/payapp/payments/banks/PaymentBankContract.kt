package com.mercadopago.payapp.payments.banks

import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.payments.models.Payment

internal interface PaymentBankContract {

    interface Presenter {

        fun onStart()

        fun onStop()

        fun onBankSelected(bank: PaymentBank)

    }

    interface View {

        fun showBanks(banks: List<PaymentBank>)

        fun showNextScreen(payment: Payment)

        fun showError(error: Throwable)

    }

}
