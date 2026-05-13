#!/usr/bin/env bash
# Scan Kotlin test files under src/test/ for remaining JUnit-style patterns.
# Exit 0  → all clean, no JUnit patterns found.
# Exit 1  → at least one file still contains JUnit patterns (file:line printed to stderr).
set -euo pipefail

JUNIT_PATTERNS='assertEquals|assertTrue|assertFalse|assertNull|assertNotNull|assertThrows|@Test|@BeforeEach|@AfterEach|import org\.junit'

found=0
while IFS= read -r -d '' file; do
    if grep -qP "$JUNIT_PATTERNS" "$file" 2>/dev/null; then
        grep -nP "$JUNIT_PATTERNS" "$file" >&2
        echo "FAIL: JUnit patterns found in $file" >&2
        found=1
    fi
done < <(find src/test -name "*.kt" -print0 2>/dev/null)

if [ "$found" -eq 0 ]; then
    echo "OK: No JUnit patterns found in src/test/**/*.kt"
fi

exit "$found"
