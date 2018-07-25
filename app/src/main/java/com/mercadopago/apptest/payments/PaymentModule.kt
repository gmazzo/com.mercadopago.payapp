package com.mercadopago.apptest.payments

import com.mercadopago.apptest.payments.amount.PaymentAmountModule
import dagger.Module

@Module(includes = [PaymentAmountModule::class])
interface PaymentModule