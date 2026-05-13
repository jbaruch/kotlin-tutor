# Changelog

All notable changes to `jbaruch/kotlin-tutor` are documented here. The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/) and the project adheres to [semantic versioning](https://semver.org/spec/v2.0.0.html).

## [0.3.0] — 2026-05-13

### Added

- **Two real-eval scenarios** closing the per-skill coverage gap flagged by `jbaruch/coding-policy`'s `plugin-evals` rule:
  - `evals/scenario-pojoify-subscription/` — exercises `pojoify-to-dataclass` (Java-style `Subscription` POJO with manual equals/hashCode/toString + bean accessors → idiomatic data class). Criteria check K-1 + K-3 specifics and field-order preservation
  - `evals/scenario-nullable-user-service/` — exercises `nullable-cleanup` (`UserRepository` returning `Optional<User>` with `.orElse` / `.ifPresent` / `.map` / `.orElseThrow` + `UserService` caller that must update alongside). Criteria check K-2 mappings and the no-`!!` invariant
- All three skills now have at least one dedicated scenario (previously only `kotlinify-tests` was covered)

### Fixed

- `nullable-cleanup` skill description was failing deterministic skill-review validation (`description must not contain XML tags`) because `Optional<T>` was parsing as an XML tag. Rephrased to use `java.util.Optional` and prose-named operators (`question-mark suffix`, `safe-call`, `elvis`, `let`). Skill review score: 0% (FAILED) → 100%
- `scenario-1/task.md` bled the spec-style answer into the task — phrases like "each test is standalone" and "structured narrative pattern" directly told the agent which Kotest spec to pick. Task rewritten to require the agent to read the test files themselves to determine structure
- `skills/pojoify-to-dataclass/SKILL.md` review score was 88% (barely passing). Ran `tessl skill review --optimize`, curated the diff per `coding-policy`'s "Disagreeing With the Reviewer" rule (kept the before/after example and prose-tightening; restored the literal "Do not skip ahead" line `skill-authoring` prescribes, the `componentN()` why-this-matters context, "Glob" over "Grep", and the `use-data-class` cross-reference). New score: 100%

### Notes

- The `coding-policy` audit that surfaced these issues lives at `jbaruch/coding-policy@0.3.20`. Recursive demonstration: the talk's META plugin found three real gaps in the talk's running-example plugin, and fixing them is documented in this changelog entry and the git log. The talk's chapter 6 META reveal at slide 70 references this iteration as live evidence the prescription pays rent

## [0.2.0] — 2026-05-13

### Added

- Three **pedagogical** eval scenarios under `evals/trial-1-task-leaks-language/`, `evals/trial-2-rubric-grades-features/`, `evals/trial-3-rubric-weights-language/`. These are *deliberately instructive* — trial 1 leaks the language name in the task and grades features only (baseline scores 100%); trial 2 fixes the task but leaves the broken rubric (baseline still 100%); trial 3 weights idiomatic Kotlin at 80% and features at 10% each (baseline drops, lift is measurable). Demonstrates the slide-59 worked example of the talk "You're Absolutely Right (and Other Lies My AI Told Me)" with real, runnable scenarios anyone can inspect in the registry.

### Notes

- The pedagogical scenarios are flagged in their `task.md` and `criteria.json` `context` fields so anyone browsing the registry understands why they look strange — trials 1 and 2 are *intentionally bad eval design*; trial 3 is the fix.
- The three originally-generated scenarios from 0.1.0 (scenario-0, scenario-1, scenario-2) are the *real* evals; the trial-N scenarios are talk material.

## [0.1.0] — 2026-05-13

### Added

- Initial plugin scaffold (`tile.json`, `README.md`, `.tileignore`, `.gitignore`, `.gitattributes`)
- Six steering rules: `prefer-val` (K-1), `nullable-question-mark` (K-2), `use-data-class` (K-3), `kotest-over-junit` (K-4), `prefer-stdlib-scope` (K-5), `extension-over-util` (K-6)
- Three skills: `kotlinify-tests` (JUnit → Kotest conversion), `pojoify-to-dataclass` (regular class → `data class` refactor), `nullable-cleanup` (`Optional<T>` → `T?`)
- One script: `scripts/verify-no-junit-assertions.sh` — CI gate delegated from the `kotlinify-tests` skill; enforces rule K-4 deterministically

### Notes

- This plugin debuts at Geecon 2026 in the talk *"You're Absolutely Right (and Other Lies My AI Told Me)"* as the running engineering example through chapter 3 (slides 22-24, A/B demo at 26-27) and chapter 5 (eval examples)
