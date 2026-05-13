# JUnit-to-Kotest Migration and Verification

## Problem Description

The payments module has committed to eliminating all JUnit-style test code. The test source tree under `src/test/kotlin/` contains files that still need migrating. A verification script at `scripts/verify-no-junit-assertions.sh` is available — it scans `src/test/` and exits non-zero if any JUnit-style patterns remain, printing the offending file and line to stderr.

Your job is to find and convert all JUnit-style test files in the test source tree, then run the verification script to confirm the migration is clean. If the script reports failures, fix the remaining issues and re-run until it exits cleanly.

## Output Specification

- Convert the test files in place (modify them within `src/test/kotlin/`)
- Write a `conversion-report.md` that includes:
  - The list of files you found and converted
  - The output and exit code of the verification script after your final run
