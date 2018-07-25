package com.mercadopago.apptest.data

import com.mercadopago.apptest.BaseTest
import com.mercadopago.apptest.mockReponse
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.`when`

class PublicKeyInterceptorTest : BaseTest() {
    private lateinit var interceptor: PublicKeyInterceptor

    @Mock
    lateinit var chain: Interceptor.Chain

    @Before
    fun setup() {
        interceptor = PublicKeyInterceptor()
    }

    @Test
    fun testIntercept() {
        val request = Request.Builder()
                .get()
                .url("http://someurl.dummy/test")
                .build()
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(requestCaptor.capture())).thenReturn(mockReponse(request))

        interceptor.intercept(chain)

        assertEquals("http://someurl.dummy/test?public_key=${BuildConfig.PUBLIC_KEY}",
                requestCaptor.value.url().toString())
    }

}
