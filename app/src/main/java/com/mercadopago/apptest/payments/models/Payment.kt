package com.mercadopago.apptest.payments.models

import android.os.Parcelable
import com.mercadopago.apptest.data.models.PaymentMethod
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payment(var amount: Int? = null,
                   var method: PaymentMethod? = null) : Parcelable
