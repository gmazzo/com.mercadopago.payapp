package com.mercadopago.payapp.data.source

import android.net.Uri
import com.mercadopago.payapp.BaseTest
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.data.models.PaymentInstallments
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.mockRetrofit
import com.mercadopago.payapp.toUri
import okhttp3.mock.ClasspathResources.resource
import okhttp3.mock.MockInterceptor
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class PaymentsDataSourceTest : BaseTest() {
    private lateinit var interceptor: MockInterceptor
    private lateinit var dataSource: PaymentsDataSource

    @Before
    fun setup() {
        interceptor = MockInterceptor()
        dataSource = PaymentsDataSource(mockRetrofit(interceptor)
                .create(PaymentsAPI::class.java))
    }

    @Test
    fun testListMethods() {
        interceptor.addRule().get()
                .path("/payment_methods")
                .respond(resource("payment_methods.json"))

        val result = dataSource.listMethods().blockingGet()

        assertThat(result, equalTo(listOf(
                PaymentMethod("visa", "Visa", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("master", "Mastercard", "http://img.mlstatic.com/org-img/MP3/API/logos/master.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("amex", "American Express", "http://img.mlstatic.com/org-img/MP3/API/logos/amex.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("cmr", "CMR", "http://img.mlstatic.com/org-img/MP3/API/logos/cmr.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("cobroexpress", "CobroExpress", "http://img.mlstatic.com/org-img/MP3/API/logos/cobroexpress.gif".toUri(), 1f, 60000f, 0)
        )))
    }

    @Test
    fun testCardIssuers() {
        interceptor.addRule().get()
                .path("/payment_methods/card_issuers")
                .param("payment_method_id", TEST_METHOD.id)
                .respond(resource("card_issuers.json"))

        val result = dataSource.cardIssuers(TEST_METHOD).blockingGet()

        assertThat(result, equalTo(listOf(
                PaymentBank(288, "Tarjeta Shopping", "http://img.mlstatic.com/org-img/MP3/API/logos/288.gif".toUri()),
                PaymentBank(272, "Banco Comafi", "http://img.mlstatic.com/org-img/MP3/API/logos/272.gif".toUri()),
                PaymentBank(279, "Banco Galicia", "http://img.mlstatic.com/org-img/MP3/API/logos/279.gif".toUri()),
                PaymentBank(1, "Otro", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif".toUri())
        )))
    }

    @Test
    fun testInstallments() {
        val amount = 101.2f

        interceptor.addRule().get()
                .path("/payment_methods/installments")
                .param("amount", amount.toString())
                .param("payment_method_id", TEST_METHOD.id)
                .param("issuer.id", TEST_BANK.id.toString())
                .respond(resource("installments.json"))

        val result = dataSource.installments(amount, TEST_METHOD, TEST_BANK).blockingGet()

        assertThat(result, equalTo(listOf(
                PaymentInstallments(1, "1 cuota de $ 100,00 ($ 100,00)"),
                PaymentInstallments(3, "3 cuotas de $ 37,97 ($ 113,92)"),
                PaymentInstallments(6, "6 cuotas de $ 21,10 ($ 126,62)"),
                PaymentInstallments(9, "9 cuotas de $ 15,55 ($ 139,93)"),
                PaymentInstallments(12, "12 cuotas de $ 12,77 ($ 153,24)"))
        ))
    }

    companion object {

        val TEST_METHOD = PaymentMethod("anId", "aMethod", Uri.parse(""), 0f, 0f, 0)
        val TEST_BANK = PaymentBank(101, "aBank", Uri.parse(""))

    }

}
