package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class PaymentTest {

    @Test
    fun processValidPaymentReturnsTransaction() {
        val processor = PaymentProcessor()
        val txn = processor.process(100.0, "USD")
        assertNotNull(txn)
        assertEquals("COMPLETED", txn.status)
    }

    @Test
    fun processNegativeAmountThrows() {
        val processor = PaymentProcessor()
        assertThrows<IllegalArgumentException> {
            processor.process(-50.0, "USD")
        }
    }

    @Test
    fun processZeroAmountThrows() {
        val processor = PaymentProcessor()
        assertThrows<IllegalArgumentException> {
            processor.process(0.0, "USD")
        }
    }
}
