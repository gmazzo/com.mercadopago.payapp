package com.mercadopago.payapp.data.source

import com.google.gson.annotations.SerializedName

internal data class Installments(
        @SerializedName("payer_costs") val payerCosts: List<PayerCost>)
