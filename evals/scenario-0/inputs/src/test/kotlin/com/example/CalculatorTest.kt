package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun addTwoPositiveNumbers() {
        val calc = Calculator()
        val result = calc.add(3, 4)
        assertEquals(7, result)
    }

    @Test
    fun subtractWithFailureMessage() {
        val calc = Calculator()
        val result = calc.subtract(10, 4)
        assertEquals(6, result, "ten minus four should be six")
    }

    @Test
    fun divideByZeroThrows() {
        val calc = Calculator()
        assertThrows<ArithmeticException> {
            calc.divide(5, 0)
        }
    }

    @Test
    fun absOfNegativeIsPositive() {
        val calc = Calculator()
        val result = calc.abs(-7)
        assertTrue(result > 0)
    }

    @Test
    fun absIsNeverNegative() {
        val calc = Calculator()
        val result = calc.abs(3)
        assertFalse(result < 0)
    }

    @Test
    fun sqrtOfNegativeReturnsNull() {
        val calc = Calculator()
        val result = calc.safeSqrt(-4.0)
        assertNull(result)
    }

    @Test
    fun sqrtOfPositiveReturnsValue() {
        val calc = Calculator()
        val result = calc.safeSqrt(9.0)
        assertNotNull(result)
    }
}
