package com.example.calculator.domain.main

import org.junit.Assert
import org.junit.Test

import com.example.calculator.domain.calculateExpression

class CalculateExpressionKtTest {

    @Test
    fun testExpression() {
        val expression = "99999999/10"
        val result = "9999999.9"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testInput() {
        testCalculation("", "")
        testCalculation("2", "2")
//        testCalculation("2+", "2")
        testCalculation("2+2", "4")
    }

    private fun testCalculation(
        expression: String,
        result: String
    ) {
        Assert.assertEquals(result, calculateExpression(expression))
    }
}