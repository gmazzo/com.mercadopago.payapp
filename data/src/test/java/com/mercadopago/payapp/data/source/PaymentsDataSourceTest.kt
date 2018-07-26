package com.mercadopago.payapp.data.source

import com.mercadopago.payapp.BaseTest
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.mockRetrofit
import com.mercadopago.payapp.toUri
import okhttp3.mock.ClasspathResources.resource
import okhttp3.mock.MockInterceptor
import okhttp3.mock.Rule
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class PaymentsDataSourceTest : BaseTest() {
    private lateinit var interceptor: MockInterceptor
    private lateinit var dataSource: PaymentsDataSource

    @Before
    fun setup() {
        interceptor = MockInterceptor().apply {
            addRule(Rule.Builder().get()
                    .path("/payment_methods")
                    .respond(resource("payment_methods.json")))
        }

        val api = mockRetrofit(interceptor)
                .create(PaymentsAPI::class.java)

        dataSource = PaymentsDataSource(api)
    }

    @Test
    fun testListMethods() {
        val result = dataSource.listMethods().blockingGet()

        assertThat(result, equalTo(listOf(
                PaymentMethod("visa", "Visa", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("master", "Mastercard", "http://img.mlstatic.com/org-img/MP3/API/logos/master.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("amex", "American Express", "http://img.mlstatic.com/org-img/MP3/API/logos/amex.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("cmr", "CMR", "http://img.mlstatic.com/org-img/MP3/API/logos/cmr.gif".toUri(), 2f, 250000f, 2880),
                PaymentMethod("cobroexpress", "CobroExpress", "http://img.mlstatic.com/org-img/MP3/API/logos/cobroexpress.gif".toUri(), 1f, 60000f, 0)
        )))
    }

}
