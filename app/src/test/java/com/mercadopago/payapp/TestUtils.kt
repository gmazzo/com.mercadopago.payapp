package com.mercadopago.payapp

import android.net.Uri
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.data.models.PaymentInstallments
import com.mercadopago.payapp.data.models.PaymentMethod

val TEST_URI = Uri.parse("http://test")

val TEST_METHOD1 = PaymentMethod("1", "name1", TEST_URI, 10f, 40f, 0)
val TEST_METHOD2 = PaymentMethod("2", "name2", TEST_URI, 0f, 100f, 2880)
val TEST_METHOD3 = PaymentMethod("3", "name3", TEST_URI, 20f, 80f, 2880)

val TEST_BANK1 = PaymentBank(1, "bank1", TEST_URI)
val TEST_BANK2 = PaymentBank(2, "bank2", TEST_URI)

fun Int.toInstallments() = PaymentInstallments(installments = this, message = "$this installments")
