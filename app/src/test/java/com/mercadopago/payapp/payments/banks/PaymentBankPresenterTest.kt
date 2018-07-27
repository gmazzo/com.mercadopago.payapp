package com.mercadopago.payapp.payments.banks

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

internal class PaymentBankPresenterTest : BaseTest() {
    private lateinit var presenter: PaymentBankPresenter

    @Mock
    private lateinit var view: PaymentBankContract.View

    @Mock
    private lateinit var header: PaymentHeader

    @Mock
    private lateinit var repository: PaymentsRepository

    @Test
    fun testOnStart1() {
        testOnStart(30f, TEST_METHOD1, TEST_BANK1)
    }

    @Test
    fun testOnStart2() {
        testOnStart(4f, TEST_METHOD2, TEST_BANK1, TEST_BANK2)
    }

    @Test
    fun testOnStart3() {
        testOnStart(100f, TEST_METHOD2)
    }

    private fun testOnStart(amount: Float, method: PaymentMethod, vararg expectedBanks: PaymentBank) {
        val payment = Payment(amount = amount, method = method)

        `when`(repository.cardIssuers(method)).thenReturn(Single.just(expectedBanks.toList()))

        presenter = PaymentBankPresenter(view, header, repository, payment)
        presenter.onStart()

        verify(repository).cardIssuers(method)
        verify(header).updatePayment(payment)
        verify(view).showBanks(expectedBanks.toList())
    }

    @Test
    fun testOnStart_Error() {
        val error = SocketTimeoutException()

        `when`(repository.cardIssuers(TEST_PAYMENT.method!!)).thenReturn(Single.error(error))

        presenter = PaymentBankPresenter(view, header, repository, TEST_PAYMENT)
        presenter.onStart()

        verify(header).updatePayment(TEST_PAYMENT)
        verify(view).showError(error)
    }

    @Test
    fun testOnBankSelected1() {
        testOnBankSelected(TEST_BANK1)
    }

    @Test
    fun testOnBankSelected2() {
        testOnBankSelected(TEST_BANK2)
    }

    private fun testOnBankSelected(bank: PaymentBank) {
        presenter = PaymentBankPresenter(view, header, repository, TEST_PAYMENT)
        presenter.onBankSelected(bank)

        verify(view).showNextScreen(TEST_PAYMENT.copy(bank = bank))
    }

    companion object {

        val TEST_PAYMENT = Payment(amount = 100f, method = TEST_METHOD1)

    }

}
