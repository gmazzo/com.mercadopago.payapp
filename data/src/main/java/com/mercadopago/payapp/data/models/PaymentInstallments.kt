package com.mercadopago.payapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentInstallments(
        val installments: Int,
        val message: String) : Parcelable
