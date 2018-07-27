package com.mercadopago.payapp.payments.installments

import com.mercadopago.payapp.payments.getPayment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PaymentInstallmentsModule {

    @Binds
    internal abstract fun bindPresenter(impl: PaymentInstallmentsPresenter): PaymentInstallmentsContract.Presenter

    @Binds
    internal abstract fun bindView(impl: PaymentInstallmentsFragment): PaymentInstallmentsContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePayment(fragment: PaymentInstallmentsFragment) = fragment.arguments!!.getPayment()

    }

}
