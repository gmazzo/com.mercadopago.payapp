package com.mercadopago.payapp.data.source

import com.google.gson.annotations.SerializedName

internal data class Method(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("payment_type_id") val type: MethodType,
        @SerializedName("status") val status: MethodStatus,
        @SerializedName("thumbnail") val thumbnail: String,
        @SerializedName("min_allowed_amount") val minAmount: Float,
        @SerializedName("max_allowed_amount") val maxAmount: Float,
        @SerializedName("accreditation_time") val accreditationTime: Int)
