package com.mercadopago.payapp.payments.resume

import com.mercadopago.payapp.payments.getPayment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PaymentResumeModule {

    @Binds
    internal abstract fun bindPresenter(impl: PaymentResumePresenter): PaymentResumeContract.Presenter

    @Binds
    internal abstract fun bindView(impl: PaymentResumeFragment): PaymentResumeContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePayment(fragment: PaymentResumeFragment) = fragment.arguments!!.getPayment()

    }

}
