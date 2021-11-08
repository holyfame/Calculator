package com.example.calculator.domain

import com.example.calculator.domain.calculateExpression
import com.fathzer.soft.javaluator.DoubleEvaluator
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import java.lang.AssertionError
import kotlin.math.floor

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
    fun testZeroDivision() {
        val result = DoubleEvaluator().evaluate("2 / 0")
        println(result % 1.0 == 0.0)
        println(result.toLong().toString())
    }

    @Test
    fun testExpression() {
        val expression = "99999999 / 10"
        val result = "9999999.9"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testPrecision() {
        val expression = "10/3"
        Assert.assertEquals("3", calculateExpression(expression, 0))
        Assert.assertEquals("3.33", calculateExpression(expression, 2))
        Assert.assertEquals("3.3333333333333335", calculateExpression(expression))
    }

    @Test
    fun testNegativePrecisionAssert() {
        val expression = "10/3"
        Assert.assertThrows(AssertionError::class.java) {
            calculateExpression(expression, -2)
        }
    }

    @Test
    fun testBraces() {
        val expression = "(2 + 5) ^ (1/2)"
        val result = calculateExpression(expression, 3)
        assertEquals("2.646", result)
    }

    @Test
    fun testInput() {
        testCalculation("", "")
        testCalculation("2", "2")
        testCalculation("2+2", "4")
    }

    private fun testCalculation (
        expression: String,
        result: String
    ) {
        Assert.assertEquals(result, calculateExpression(expression))
    }

}