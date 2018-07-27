package com.mercadopago.payapp.data.source

import com.google.gson.annotations.SerializedName

internal data class Bank(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("thumbnail") val thumbnail: String)
