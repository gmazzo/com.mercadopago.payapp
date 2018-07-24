package com.mercadopago.apptest.data.payments

import com.mercadopago.apptest.data.models.PaymentMethod
import com.mercadopago.apptest.mockRetrofit
import com.mercadopago.apptest.toUri
import okhttp3.mock.ClasspathResources.resource
import okhttp3.mock.MockInterceptor
import okhttp3.mock.Rule
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PaymentsDataSourceTest {
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
                PaymentMethod("visa", "Visa", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif".toUri(), 2, 250000),
                PaymentMethod("master", "Mastercard", "http://img.mlstatic.com/org-img/MP3/API/logos/master.gif".toUri(), 2, 250000),
                PaymentMethod("amex", "American Express", "http://img.mlstatic.com/org-img/MP3/API/logos/amex.gif".toUri(), 2, 250000)
        )))
    }

}
