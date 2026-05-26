# Modernise Authentication Credential Model

## Background

The integrations team inherited a `ServiceCredential` class that was originally ported from a Java microservice. It carries authentication tokens for third-party API connections and is used internally within the `com.integrations.auth` module — no external library consumers depend on it. The class has accumulated the usual Java boilerplate: hand-written accessors, manually overridden object methods, and mutable properties set one-by-one at construction time.

The team wants to modernise it to idiomatic Kotlin. The class and its companion class `TokenRefresher` are in the `inputs/` directory. `TokenRefresher` is the only caller that uses the bean-style setter methods.

## What to Produce

Refactor `ServiceCredential` to idiomatic Kotlin and update `TokenRefresher` so the code compiles against the new shape. Write your changes to `ServiceCredential.kt` and `TokenRefresher.kt` (you can edit the files in `inputs/` directly). Produce a brief `conversion-summary.md` describing what changed and flagging anything that required a deliberate decision.
