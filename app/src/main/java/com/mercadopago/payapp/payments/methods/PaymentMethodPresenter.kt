package com.mercadopago.payapp.payments.methods

import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.data.payments.PaymentsRepository
import com.mercadopago.payapp.payments.models.Payment
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

internal class PaymentMethodPresenter @Inject constructor(
        private val view: PaymentMethodContract.View,
        private val repository: PaymentsRepository,
        private val payment: Payment) : PaymentMethodContract.Presenter {

    override fun onStartLoading() {
        repository.listMethods()
                .map { it.filter { payment.amount!! in it.minAmount..it.maxAmount } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showMethods, view::showError)
    }

    override fun onMethodSelected(method: PaymentMethod) {
        view.showNextScreen(payment.copy(method = method))
    }

}
