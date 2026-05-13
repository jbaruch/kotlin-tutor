# User Repository API Modernisation

## Problem Description

The platform team owns a `UserRepository` and a `UserService` that calls into it. Both files live under `inputs/` (`UserRepository.kt`, `UserService.kt`). The repository's public surface was designed to defend against the absence of a user — every lookup operation returns a wrapped value, and the service handles the wrapping explicitly at each call site.

The team has been modernising older API surfaces to match current project conventions. The `UserRepository` is the next in the queue. The change must:

- Update the repository's public surface to the current convention for "may be absent"
- Update `UserService` (the caller) so it still compiles against the modernised surface
- Preserve the externally-observable behaviour — every operation must produce the same result for the same input as it did before

## Output Specification

Write the modernised files to `output/UserRepository.kt` and `output/UserService.kt`. Both files must compile against each other after the change. No new operator is introduced to silence the compiler — null handling stays explicit.
