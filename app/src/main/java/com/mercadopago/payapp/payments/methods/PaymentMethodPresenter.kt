package com.mercadopago.payapp.payments.methods

import com.mercadopago.payapp.data.PaymentsRepository
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import javax.inject.Inject

internal class PaymentMethodPresenter @Inject constructor(
        private val view: PaymentMethodContract.View,
        private val header: PaymentHeader,
        private val repository: PaymentsRepository,
        private val payment: Payment) : PaymentMethodContract.Presenter {
    private var disposable = Disposables.disposed()

    override fun onStart() {
        header.updatePayment(payment)

        disposable.dispose()
        disposable = repository.listMethods()
                .map { it.filter { payment.amount!! in it.minAmount..it.maxAmount } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showMethods, view::showError)
    }

    override fun onStop() {
        disposable.dispose()
    }

    override fun onMethodSelected(method: PaymentMethod) {
        payment.method = method

        view.showNextScreen()
    }

}
