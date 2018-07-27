package com.mercadopago.payapp.data.source

import com.google.gson.annotations.SerializedName

internal data class PayerCost(
        @SerializedName("installments") val installments: Int,
        @SerializedName("recommended_message") val message: String)
