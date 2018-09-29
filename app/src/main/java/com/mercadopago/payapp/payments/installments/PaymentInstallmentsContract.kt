package com.mercadopago.payapp.payments.installments

import com.mercadopago.payapp.data.models.PaymentInstallments

internal interface PaymentInstallmentsContract {

    interface Presenter {

        fun onStart()

        fun onStop()

        fun onInstallmentsSelected(installments: PaymentInstallments)

    }

    interface View {

        fun showInstallments(installments: List<PaymentInstallments>)

        fun showNextScreen()

        fun showError(error: Throwable)

    }

}
