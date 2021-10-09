package com.example.calculator.data

import com.example.calculator.domain.calculateExpression
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class CalculateExpressionTest : TestCase() {

    @Test
    fun testPlus() {
        Assert.assertEquals("4", calculateExpression("2 + 2"))
    }

    @Test
    fun testMinus() {
        Assert.assertEquals("0", calculateExpression("2 - 2"))
    }

    @Test
    fun testDivide() {
        Assert.assertEquals("1", calculateExpression("2 / 2"))
    }

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