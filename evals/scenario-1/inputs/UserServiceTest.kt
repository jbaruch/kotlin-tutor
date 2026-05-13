package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class UserServiceTest {

    @Test
    fun givenValidEmail_whenRegisterUser_thenUserIsCreated() {
        val service = UserService()
        val user = service.register("alice@example.com", "Alice")
        assertEquals("alice@example.com", user.email)
    }

    @Test
    fun givenDuplicateEmail_whenRegisterUser_thenExceptionThrown() {
        val service = UserService()
        service.register("bob@example.com", "Bob")
        assertThrows<IllegalArgumentException> {
            service.register("bob@example.com", "Robert")
        }
    }

    @Test
    fun givenUnknownId_whenFindById_thenReturnsNull() {
        val service = UserService()
        val user = service.findById(999L)
        assertNull(user)
    }

    @Test
    fun givenExistingUser_whenFindByEmail_thenReturnsUser() {
        val service = UserService()
        service.register("carol@example.com", "Carol")
        val found = service.findByEmail("carol@example.com")
        assertEquals("Carol", found?.name)
    }
}
