package com.example.calculator.domain

import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class FormatForHistoryKtTest {
    @Test
    fun testFormatter() {
        val date = LocalDateTime.of(2021, 10, 25, 10, 2, 3)
        val result = formatForHistory(date)
        Assert.assertEquals("10:02:03 October 25", result)
    }

    @Test
    fun testFormatterNow() {
        val date = LocalDateTime.of(2021, 10, 25, 19, 2, 3)
        val result = formatForHistory(date)
        Assert.assertEquals("19:02:03 October 25", result)
    }
}