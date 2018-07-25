package com.mercadopago.apptest

import android.content.Context
import com.mercadopago.apptest.data.DataModule
import com.mercadopago.apptest.payments.PaymentFragment
import com.mercadopago.apptest.payments.PaymentModule
import com.mercadopago.dagger.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DataModule::class])
internal interface ApplicationModule {

    @Binds
    fun bindContext(impl: Application): Context

    @PerFragment
    @ContributesAndroidInjector(modules = [PaymentModule::class])
    fun providePaymentFragment(): PaymentFragment

}
