# Test Suite Migration for Core Utilities

## Problem Description

The platform team maintains test suites for two components: a StringFormatter utility and a UserService. Both were written in JUnit 5 some time ago, and the team has been modernising older test files to fit current project conventions.

The files are:

- `inputs/StringFormatterTest.kt`
- `inputs/UserServiceTest.kt`

Each file has its own character — read the existing test code to understand how each one is shaped before picking a target style. The team expects the migration to honour the structural intent of each file, not just mechanically port annotations.

## Output Specification

Write the converted files to `output/StringFormatterTest.kt` and `output/UserServiceTest.kt`.

Every existing test case must be preserved with its intent intact. Any per-test setup logic must continue to run before each test.
