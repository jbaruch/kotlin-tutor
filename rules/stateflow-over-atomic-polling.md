---
alwaysApply: true
---

# StateFlow Over AtomicReference + Polling

## The Anti-Pattern

- A coroutine that busy-polls an `AtomicReference<T?>` for new values — `while (true) { val x = ref.get(); if (x == null) { delay(20); continue }; ... }` — is Java-in-Kotlin
- The shape combines `j.u.c.atomic` (a lock-free primitive sized for CAS-heavy contention) with a `delay`-based poll loop (which burns timer ticks and adds latency) for what is actually a single-writer / many-reader signal
- This is the highest-frequency miss when an agent trained on pre-coroutines JVM material ports reactive state into a coroutine codebase

## The Idiomatic Replacement

- For single-writer, many-reader state that consumers want to react to, use **`MutableStateFlow<T>`** (`kotlinx.coroutines.flow.MutableStateFlow`)
- Reader side: `latest.filterNotNull().collect { ... }` — suspends until the next value, zero CPU when idle, no polling loop
- Writer side: `latest.value = newValue` — reads naturally, no `.set(...)` / `.get()` ceremony
- Conflation is built in: a slow consumer naturally drops intermediate values, which is exactly what live signals (video frames, sensor readings, status snapshots) want

## When `AtomicReference` Is Still Right

- You genuinely need lock-free CAS semantics — `compareAndSet`, `getAndUpdate`, etc. — to coordinate across non-coroutine threads
- No consumer wants to react to value changes; the reference is read on demand only, never waited on

## The Trigger Smell

- If you write or see `while (true) { val x = ref.get(); if (x == null) delay(...); continue }`, replace the atomic with `MutableStateFlow` and the loop with `.collect`
- Same trigger applies to any `do { ... } while (state == ...)` busy-poll guarding against an "is the value ready yet?" condition — that's a `Flow` or a `Channel.receive()`, not an atomic and a sleep
- Cross-reference: this rule is the specific case `coroutines-for-concurrency`'s "Flow over Channel for streams" section anticipates; a `StateFlow` replacing an atomic-plus-poll loop is one canonical use of that guidance

## Anti-patterns

- ❌ `AtomicReference<T?>` + polling `delay` loop as a state-change signal
- ❌ Long-running `while (true) { delay(...) }` reader in a coroutine — coroutines are *built* to avoid this
- ❌ `@Volatile var` + polling — same pattern, different decoration
