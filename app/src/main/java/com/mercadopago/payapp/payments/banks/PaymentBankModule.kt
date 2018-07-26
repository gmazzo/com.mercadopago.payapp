package com.mercadopago.payapp.payments.banks

import com.mercadopago.payapp.payments.getPayment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PaymentBankModule {

    @Binds
    internal abstract fun bindPresenter(impl: PaymentBankPresenter): PaymentBankContract.Presenter

    @Binds
    internal abstract fun bindView(impl: PaymentBankFragment): PaymentBankContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePayment(fragment: PaymentBankFragment) = fragment.arguments!!.getPayment()

    }

}
