package com.example.calculator.domain

import com.fathzer.soft.javaluator.DoubleEvaluator
import kotlin.math.floor

/**
 * Рассчитывает значение выражения [expression]
 */
fun calculateExpression(expression: String): String {

    if (expression.isBlank()) return ""

    val result = DoubleEvaluator().evaluate(expression)

    return if (floor(result) == result) {
        result.toInt().toString()
    } else {
        result.toString()
    }
}