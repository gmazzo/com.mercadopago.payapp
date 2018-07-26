package com.mercadopago.payapp.payments.methods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mercadopago.payapp.R
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.payments.ARG_PAYMENT
import com.mercadopago.payapp.payments.models.Payment
import com.mercadopago.payapp.toast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_payment_methods.*
import javax.inject.Inject

class PaymentMethodFragment : DaggerFragment(), PaymentMethodContract.View {

    @Inject
    internal lateinit var presenter: PaymentMethodContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_methods, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recycler.layoutManager as FlexboxLayoutManager) {
            justifyContent = JustifyContent.CENTER
        }
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

    override fun showMethods(methods: List<PaymentMethod>) {
        recycler.adapter = PaymentMethodAdapter(methods, presenter::onMethodSelected)
        switcher.displayedChild = 1
    }

    override fun showError(error: Throwable) {
        error.toast(context)
    }

    override fun showNextScreen(payment: Payment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        fun create(payment: Payment) = PaymentMethodFragment().apply {
            arguments = bundleOf(ARG_PAYMENT to payment)
        }

    }

}
