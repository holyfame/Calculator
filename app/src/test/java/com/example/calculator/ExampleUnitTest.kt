package com.example.calculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val four: Int = 4
        assertEquals(4, four)
    }

    @Test
    fun twoPlusTwo() {
        assertNotEquals("trump", "biden")
    }
}