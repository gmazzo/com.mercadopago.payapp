package com.mercadopago.apptest.payments.amount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercadopago.apptest.R
import com.mercadopago.apptest.payments.methods.PaymentMethodsFragment
import com.mercadopago.apptest.payments.models.Payment
import com.mercadopago.apptest.showError
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_payment_amount.*
import javax.inject.Inject

internal class PaymentAmountFragment : DaggerFragment(), PaymentAmountContract.View {

    @Inject
    lateinit var presenter: PaymentAmountContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_amount, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        amount.editText!!.setOnEditorActionListener { _, _, _ -> next.performClick() }
        next.setOnClickListener { _ ->
            val amount = amount.editText!!.text.toString().toIntOrNull()

            presenter.onAmountEntered(amount)
        }
    }

    override fun showAmountError() {
        amount.showError = getText(R.string.err_payment_amount)
    }

    override fun showNextScreen(payment: Payment) {
        fragmentManager!!.beginTransaction()
                .replace(id, PaymentMethodsFragment.create(payment))
                .addToBackStack(null)
                .commit()
    }

}
