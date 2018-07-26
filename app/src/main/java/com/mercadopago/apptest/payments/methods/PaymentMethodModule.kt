package com.mercadopago.apptest.payments.methods

import com.mercadopago.apptest.payments.ARG_PAYMENT
import com.mercadopago.apptest.payments.models.Payment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PaymentMethodModule {

    @Binds
    internal abstract fun bindPresenter(impl: PaymentMethodPresenter): PaymentMethodContract.Presenter

    @Binds
    internal abstract fun bindView(impl: PaymentMethodFragment): PaymentMethodContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePayment(fragment: PaymentMethodFragment) =
                fragment.arguments!!.getParcelable(ARG_PAYMENT) as Payment

    }

}
