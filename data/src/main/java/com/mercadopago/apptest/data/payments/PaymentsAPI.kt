package com.mercadopago.apptest.data.payments

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET

internal interface PaymentsAPI {

    @GET("payment_methods")
    fun paymentMethods(): Single<List<Method>>

    data class Method(
            @SerializedName("id") val id: String,
            @SerializedName("name") val name: String,
            @SerializedName("payment_type_id") val type: MethodType,
            @SerializedName("status") val status: MethodStatus,
            @SerializedName("thumbnail") val thumbnail: String,
            @SerializedName("min_allowed_amount") val minAmount: Int,
            @SerializedName("max_allowed_amount") val maxAmount: Int)

    enum class MethodType {

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

    enum class MethodStatus {

        @SerializedName("active")
        ACTIVE,

        @SerializedName("deactive")
        DEACTIVE,

        @SerializedName("temporally_deactive")
        TEMPORALLY_DEACTIVE;

        val available: Boolean get() = this == ACTIVE

    }

}
