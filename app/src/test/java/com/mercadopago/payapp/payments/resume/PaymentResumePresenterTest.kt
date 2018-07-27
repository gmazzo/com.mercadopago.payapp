package com.mercadopago.payapp.payments.resume

import com.mercadopago.payapp.*
import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

class PaymentResumePresenterTest : BaseTest() {

    private lateinit var presenter: PaymentResumePresenter

    @Mock
    private lateinit var header: PaymentHeader

    @Mock
    private lateinit var view: PaymentResumeContract.View

    @Test
    fun testOnStart1() {
        testOnStart(Payment(amount = 100f))
    }

    @Test
    fun testOnStart2() {
        testOnStart(Payment(amount = 30f, method = TEST_METHOD1))
    }

    @Test
    fun testOnStart3() {
        testOnStart(Payment(amount = 40.2f, method = TEST_METHOD2, bank = TEST_BANK2, installments = 3.toInstallments()))
    }

    private fun testOnStart(payment: Payment) {
        presenter = PaymentResumePresenter(view, header, payment)
        presenter.onStart()

        verify(header).updatePayment(null)
        verify(view).showPayment(payment)
    }

}
