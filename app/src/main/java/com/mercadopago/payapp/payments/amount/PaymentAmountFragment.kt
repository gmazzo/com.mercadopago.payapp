package com.mercadopago.payapp.payments.amount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercadopago.payapp.R
import com.mercadopago.payapp.payments.methods.PaymentMethodFragment
import com.mercadopago.payapp.replaceWith
import com.mercadopago.payapp.showError
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_payment_amount.*
import javax.inject.Inject

class PaymentAmountFragment : DaggerFragment(), PaymentAmountContract.View {

    @Inject
    internal lateinit var presenter: PaymentAmountContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_amount, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        amount.editText!!.setOnEditorActionListener { _, _, _ -> next.performClick() }
        next.setOnClickListener { _ ->
            val amount = amount.editText!!.text.toString().toFloatOrNull()

            presenter.onAmountEntered(amount)
        }
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }

    override fun showAmountError() {
        amount.showError = getText(R.string.err_payment_amount)
    }

    override fun showNextScreen() {
        replaceWith(PaymentMethodFragment())
    }

}
