package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StringFormatterTest {

    private lateinit var formatter: StringFormatter

    @BeforeEach
    fun setUp() {
        formatter = StringFormatter(locale = "en-US")
    }

    @Test
    fun convertsToUppercase() {
        assertEquals("HELLO WORLD", formatter.toUpperCase("hello world"))
    }

    @Test
    fun trimsLeadingAndTrailingWhitespace() {
        assertEquals("hello", formatter.trim("  hello  "))
    }

    @Test
    fun capitalizesFirstLetter() {
        assertEquals("Hello world", formatter.capitalize("hello world"))
    }

    @Test
    fun replacesSpacesWithUnderscores() {
        assertEquals("hello_world", formatter.slugify("hello world"))
    }
}
