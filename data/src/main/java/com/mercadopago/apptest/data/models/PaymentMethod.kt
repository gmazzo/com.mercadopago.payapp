package com.mercadopago.apptest.data.models

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class PaymentMethod(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("image") val image: Uri,
        @SerializedName("min_allowed_amount") val minAmount: Int,
        @SerializedName("max_allowed_amount") val maxAmount: Int)
