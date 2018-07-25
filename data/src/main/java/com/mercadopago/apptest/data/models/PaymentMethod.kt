package com.mercadopago.apptest.data.models

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import  kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentMethod(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("image") val image: Uri,
        @SerializedName("min_allowed_amount") val minAmount: Int,
        @SerializedName("max_allowed_amount") val maxAmount: Int) : Parcelable
