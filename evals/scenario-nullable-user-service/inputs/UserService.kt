package com.example.users

class UserService(
    private val repo: UserRepository,
    private val mailer: Mailer,
) {

    fun greet(id: String): String {
        return repo.findById(id)
            .map { "Hi, ${it.displayName}!" }
            .orElse("Hi there!")
    }

    fun emailIfPresent(id: String, body: String) {
        repo.findById(id).ifPresent { mailer.send(it.email, body) }
    }

    fun displayNameOrFallback(id: String, fallback: String): String {
        return repo.findById(id)
            .map { it.displayName }
            .orElse(fallback)
    }
}
