package com.mercadopago.payapp.payments.installments

import com.mercadopago.payapp.data.models.PaymentInstallments
import com.mercadopago.payapp.payments.models.Payment

internal interface PaymentInstallmentsContract {

    interface Presenter {

        fun onStart()

        fun onStop()

        fun onInstallmentsSelected(installments: PaymentInstallments)

    }

    interface View {

        fun showInstallments(installments: List<PaymentInstallments>)

        fun showNextScreen(payment: Payment)

        fun showError(error: Throwable)

    }

}
