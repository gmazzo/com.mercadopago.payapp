package com.mercadopago.apptest.payments.amount

import dagger.Binds
import dagger.Module

@Module
abstract class PaymentAmountModule {

    @Binds
    internal abstract fun bindPresenter(impl: PaymentAmountPresenter): PaymentAmountContract.Presenter

    @Binds
    internal abstract fun bindView(impl: PaymentAmountFragment): PaymentAmountContract.View

}