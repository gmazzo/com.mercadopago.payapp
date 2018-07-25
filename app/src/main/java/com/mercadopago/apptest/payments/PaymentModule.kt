package com.mercadopago.apptest.payments

import com.mercadopago.apptest.payments.models.Payment
import com.mercadopago.dagger.PerFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class PaymentModule {

    @ContributesAndroidInjector
    internal abstract fun providePaymentAmountFragment(): PaymentAmountFragment

    @Module
    companion object {

        @JvmStatic
        @PerFragment
        @Provides
        fun providePayment() = Payment(null, null)

    }

}
