package com.mercadopago.payapp.payments.banks

import com.mercadopago.payapp.data.PaymentsRepository
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import javax.inject.Inject

internal class PaymentBankPresenter @Inject constructor(
        private val view: PaymentBankContract.View,
        private val header: PaymentHeader,
        private val repository: PaymentsRepository,
        private val payment: Payment) : PaymentBankContract.Presenter {

    private var disposable = Disposables.disposed()

    override fun onStart() {
        header.updatePayment(payment)

        disposable.dispose()
        disposable = repository.cardIssuers(payment.method!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showBanks, view::showError)
    }

    override fun onStop() {
        disposable.dispose()
    }

    override fun onBankSelected(bank: PaymentBank) {
        payment.bank = bank

        view.showNextScreen()
    }

}
