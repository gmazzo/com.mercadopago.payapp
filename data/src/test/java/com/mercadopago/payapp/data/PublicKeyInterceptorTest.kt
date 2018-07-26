package com.mercadopago.payapp.data

import com.mercadopago.payapp.BaseTest
import com.mercadopago.payapp.mockReponse
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.`when`

class PublicKeyInterceptorTest : BaseTest() {
    private val interceptor = PublicKeyInterceptor(TEST_KEY)

    @Mock
    lateinit var chain: Interceptor.Chain

    @Test
    fun testIntercept() {
        val request = Request.Builder().get().url(TEST_URL).build()
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(requestCaptor.capture())).thenReturn(mockReponse(request))

        interceptor.intercept(chain)

        assertEquals("$TEST_URL?public_key=$TEST_KEY",
                requestCaptor.value.url().toString())
    }

    companion object {

        const val TEST_KEY = "aKey"
        const val TEST_URL = "http://someurl.dummy/test"

    }

}
