package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AccountTest {

    @Test
    fun newAccountHasZeroBalance() {
        val account = Account("ACC-001")
        assertEquals(0.0, account.balance)
    }

    @Test
    fun depositIncreasesBalance() {
        val account = Account("ACC-001")
        account.deposit(100.0)
        assertTrue(account.balance > 0)
    }

    @Test
    fun withdrawReducesBalance() {
        val account = Account("ACC-001")
        account.deposit(200.0)
        account.withdraw(50.0)
        assertEquals(150.0, account.balance)
    }
}
