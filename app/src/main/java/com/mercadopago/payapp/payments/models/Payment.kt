package com.mercadopago.payapp.payments.models

import android.os.Parcelable
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.data.models.PaymentInstallments
import com.mercadopago.payapp.data.models.PaymentMethod
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payment(var amount: Float? = null,
                   var method: PaymentMethod? = null,
                   var bank: PaymentBank? = null,
                   var installments: PaymentInstallments? = null) : Parcelable
