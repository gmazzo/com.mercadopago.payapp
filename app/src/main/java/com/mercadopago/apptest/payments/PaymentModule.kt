package com.mercadopago.apptest.payments

import com.mercadopago.apptest.payments.amount.PaymentAmountFragment
import com.mercadopago.apptest.payments.amount.PaymentAmountModule
import com.mercadopago.apptest.payments.methods.PaymentMethodFragment
import com.mercadopago.apptest.payments.methods.PaymentMethodModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PaymentModule {

    @ContributesAndroidInjector(modules = [PaymentAmountModule::class])
    internal abstract fun providePaymentAmountFragment(): PaymentAmountFragment

    @ContributesAndroidInjector(modules = [PaymentMethodModule::class])
    internal abstract fun providePaymentMethodsFragment(): PaymentMethodFragment

}
