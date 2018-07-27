package com.mercadopago.payapp.payments.installments

import com.mercadopago.payapp.data.PaymentsRepository
import com.mercadopago.payapp.data.models.PaymentInstallments
import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import javax.inject.Inject

internal class PaymentInstallmentsPresenter @Inject constructor(
        private val view: PaymentInstallmentsContract.View,
        private val header: PaymentHeader,
        private val repository: PaymentsRepository,
        private val payment: Payment) : PaymentInstallmentsContract.Presenter {

    private var disposable = Disposables.disposed()

    override fun onStart() {
        header.updatePayment(payment)

        disposable.dispose()
        disposable = repository.installments(payment.amount!!, payment.method!!, payment.bank!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showInstallments, view::showError)
    }

    override fun onStop() {
        disposable.dispose()
    }

    override fun onInstallmentsSelected(installments: PaymentInstallments) {
        view.showNextScreen(payment.copy(installments = installments))
    }

}
