package com.mercadopago.apptest.payments.methods

import com.mercadopago.apptest.data.models.PaymentMethod
import com.mercadopago.apptest.data.payments.PaymentsRepository
import com.mercadopago.apptest.payments.models.Payment
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
