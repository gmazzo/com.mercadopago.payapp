package com.mercadopago.payapp.payments

import com.mercadopago.payapp.payments.amount.PaymentAmountFragment
import com.mercadopago.payapp.payments.amount.PaymentAmountModule
import com.mercadopago.payapp.payments.methods.PaymentMethodFragment
import com.mercadopago.payapp.payments.methods.PaymentMethodModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PaymentModule {

    @ContributesAndroidInjector(modules = [PaymentAmountModule::class])
    internal abstract fun providePaymentAmountFragment(): PaymentAmountFragment

    @ContributesAndroidInjector(modules = [PaymentMethodModule::class])
    internal abstract fun providePaymentMethodsFragment(): PaymentMethodFragment

}
