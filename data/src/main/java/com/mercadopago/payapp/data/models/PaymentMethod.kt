package com.mercadopago.payapp.data.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentMethod(
        val id: String,
        val name: String,
        val image: Uri,
        val minAmount: Float,
        val maxAmount: Float,
        val accreditationMinutes: Int) : Parcelable
