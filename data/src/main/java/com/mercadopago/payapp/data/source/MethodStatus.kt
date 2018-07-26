package com.mercadopago.payapp.data.source

import com.google.gson.annotations.SerializedName

internal enum class MethodStatus {

    @SerializedName("active")
    ACTIVE,

    @SerializedName("deactive")
    DEACTIVE,

    @SerializedName("temporally_deactive")
    TEMPORALLY_DEACTIVE;

    val available: Boolean get() = this == ACTIVE

}
