package com.mercadopago.payapp.payments.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercadopago.payapp.R
import com.mercadopago.payapp.payments.PaymentHeaderFragment
import com.mercadopago.payapp.payments.models.Payment
import kotlinx.android.synthetic.main.fragment_payment_resume.*
import javax.inject.Inject

class PaymentResumeFragment : PaymentHeaderFragment(), PaymentResumeContract.View {

    @Inject
    internal lateinit var presenter: PaymentResumeContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_resume, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pay.setOnClickListener {
            flipper.displayedChild = 1
        }
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }

    override fun showPayment(payment: Payment) {
        updatePayment(payment)
    }

}
