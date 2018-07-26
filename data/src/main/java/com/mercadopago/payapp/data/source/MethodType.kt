package com.mercadopago.payapp.data.source

import com.google.gson.annotations.SerializedName

internal enum class MethodType {

    @SerializedName("ticket")
    TICKET,

    @SerializedName("atm")
    ATM,

    @SerializedName("credit_card")
    CREDIT_CARD,

    @SerializedName("debit_card")
    DEBIT_CARD,

    @SerializedName("prepaid_card")
    PREPAID_CARD

}
