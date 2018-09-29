package com.mercadopago.payapp.payments.banks

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercadopago.payapp.R
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.payments.installments.PaymentInstallmentsFragment
import com.mercadopago.payapp.replaceWith
import com.mercadopago.payapp.toast
import com.mercadopago.utils.ItemsAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class PaymentBankFragment : DaggerFragment(), PaymentBankContract.View {

    @Inject
    internal lateinit var presenter: PaymentBankContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_banks, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.layoutManager = LinearLayoutManager(context!!)
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

    override fun showBanks(banks: List<PaymentBank>) {
        recycler.adapter = ItemsAdapter(banks, presenter::onBankSelected, ::PaymentBankViewHolder)
        flipper.displayedChild = if (banks.isEmpty()) 2 else 1
    }

    override fun showError(error: Throwable) {
        error.toast(context)
    }

    override fun showNextScreen() {
        replaceWith(PaymentInstallmentsFragment())
    }

}
