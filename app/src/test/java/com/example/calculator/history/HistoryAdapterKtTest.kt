package com.example.calculator.history

import com.example.calculator.presentation.history.formatForHistory
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class HistoryAdapterKtTest {

    @Test
    fun testFormatter() {
        val date = LocalDateTime.of(2021, 10, 25, 10, 2, 3)
        val result = formatForHistory(date)
        assertEquals("10:02:03 October 25", result)
    }

    @Test
    fun testFormatterNow() {
        val date = LocalDateTime.of(2021, 10, 25, 19, 2, 3)
        val result = formatForHistory(date)
        assertEquals("19:02:03 October 25", result)
    }

}