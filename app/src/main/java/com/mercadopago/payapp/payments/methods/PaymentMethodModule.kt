package com.mercadopago.payapp.payments.methods

import dagger.Binds
import dagger.Module

@Module
internal interface PaymentMethodModule {

    @Binds
    fun bindPresenter(impl: PaymentMethodPresenter): PaymentMethodContract.Presenter

    @Binds
    fun bindView(impl: PaymentMethodFragment): PaymentMethodContract.View

}
