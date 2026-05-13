package com.example.users

import java.util.Optional

data class User(val id: String, val email: String, val displayName: String)

interface Mailer {
    fun send(to: String, subject: String)
}

class UserRepository {

    private val store: MutableMap<String, User> = mutableMapOf()

    fun findById(id: String): Optional<User> {
        return Optional.ofNullable(store[id])
    }

    fun findByEmail(email: String): Optional<User> {
        val match = store.values.firstOrNull { it.email == email }
        return if (match != null) Optional.of(match) else Optional.empty()
    }

    fun displayNameFor(id: String): String {
        return findById(id)
            .map { it.displayName }
            .orElse("Anonymous")
    }

    fun sendWelcome(id: String, mailer: Mailer) {
        findById(id).ifPresent { user ->
            mailer.send(user.email, "Welcome aboard")
        }
    }

    fun requireById(id: String): User {
        return findById(id).orElseThrow {
            NoSuchElementException("user $id not found")
        }
    }
}
