package com.mercadopago.payapp.payments

import com.mercadopago.payapp.MainActivity
import com.mercadopago.payapp.R
import com.mercadopago.payapp.payments.amount.PaymentAmountFragment
import com.mercadopago.payapp.payments.amount.PaymentAmountModule
import com.mercadopago.payapp.payments.banks.PaymentBankFragment
import com.mercadopago.payapp.payments.banks.PaymentBankModule
import com.mercadopago.payapp.payments.methods.PaymentMethodFragment
import com.mercadopago.payapp.payments.methods.PaymentMethodModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class PaymentModule {

    @ContributesAndroidInjector
    abstract fun providePaymentHeaderFragment(): PaymentHeaderFragment

    @ContributesAndroidInjector(modules = [PaymentAmountModule::class])
    abstract fun providePaymentAmountFragment(): PaymentAmountFragment

    @ContributesAndroidInjector(modules = [PaymentMethodModule::class])
    abstract fun providePaymentMethodFragment(): PaymentMethodFragment

    @ContributesAndroidInjector(modules = [PaymentBankModule::class])
    abstract fun providePaymentBankFragment(): PaymentBankFragment

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providePaymentHeader(activity: MainActivity) = activity.supportFragmentManager
                .findFragmentById(R.id.fragmentHeaderContainer) as PaymentHeader

    }

}
