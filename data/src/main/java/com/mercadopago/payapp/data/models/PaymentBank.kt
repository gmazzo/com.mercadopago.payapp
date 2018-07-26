package com.mercadopago.payapp.data.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentBank(
        val id: String,
        val name: String,
        val image: Uri) : Parcelable
