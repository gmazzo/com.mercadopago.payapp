package com.mercadopago.payapp.payments.models

import android.os.Parcelable
import com.mercadopago.payapp.data.models.PaymentMethod
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payment(val amount: Float? = null,
                   val method: PaymentMethod? = null) : Parcelable
