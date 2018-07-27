package com.mercadopago.payapp.payments.installments

import com.mercadopago.payapp.*
import com.mercadopago.payapp.data.PaymentsRepository
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.net.SocketTimeoutException

internal class PaymentInstallmentsPresenterTest : BaseTest() {
    private lateinit var presenter: PaymentInstallmentsPresenter

    @Mock
    private lateinit var view: PaymentInstallmentsContract.View

    @Mock
    private lateinit var header: PaymentHeader

    @Mock
    private lateinit var repository: PaymentsRepository

    @Test
    fun testOnStart1() {
        testOnStart(30f, TEST_METHOD1, TEST_BANK1, 1, 2, 3)
    }

    @Test
    fun testOnStart2() {
        testOnStart(4f, TEST_METHOD2, TEST_BANK2, 1)
    }

    private fun testOnStart(amount: Float, method: PaymentMethod, bank: PaymentBank, vararg expectedInstallments: Int) {
        val payment = Payment(amount = amount, method = method, bank = bank)
        val expected = expectedInstallments.map { it.toInstallments() }.toList()

        `when`(repository.installments(amount, method, bank)).thenReturn(Single.just(expected))

        presenter = PaymentInstallmentsPresenter(view, header, repository, payment)
        presenter.onStart()

        verify(repository).installments(amount, method, bank)
        verify(header).updatePayment(payment)
        verify(view).showInstallments(expected)
    }

    @Test
    fun testOnStart_Error() {
        val error = SocketTimeoutException()

        `when`(repository.installments(TEST_PAYMENT.amount!!, TEST_PAYMENT.method!!, TEST_PAYMENT.bank!!)).thenReturn(Single.error(error))

        presenter = PaymentInstallmentsPresenter(view, header, repository, TEST_PAYMENT)
        presenter.onStart()

        verify(header).updatePayment(TEST_PAYMENT)
        verify(view).showError(error)
    }

    @Test
    fun testOnInstallmentsSelected1() {
        testOnInstallmentsSelected(1)
    }

    @Test
    fun testOnInstallmentsSelected2() {
        testOnInstallmentsSelected(3)
    }

    private fun testOnInstallmentsSelected(installments: Int) {
        presenter = PaymentInstallmentsPresenter(view, header, repository, TEST_PAYMENT)
        presenter.onInstallmentsSelected(installments.toInstallments())

        verify(view).showNextScreen(TEST_PAYMENT.copy(installments = installments.toInstallments()))
    }

    companion object {

        val TEST_PAYMENT = Payment(amount = 100f, method = TEST_METHOD1, bank = TEST_BANK1)

    }

}
