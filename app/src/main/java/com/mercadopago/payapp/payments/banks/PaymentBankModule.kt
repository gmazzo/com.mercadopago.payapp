package com.mercadopago.payapp.payments.banks

import dagger.Binds
import dagger.Module

@Module
internal interface PaymentBankModule {

    @Binds
    fun bindPresenter(impl: PaymentBankPresenter): PaymentBankContract.Presenter

    @Binds
    fun bindView(impl: PaymentBankFragment): PaymentBankContract.View

}
