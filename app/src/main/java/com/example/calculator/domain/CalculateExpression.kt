package com.example.calculator.domain

import com.fathzer.soft.javaluator.DoubleEvaluator
import kotlin.math.floor

/**
 * calculate expression [expression]
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

/**
 * calculate expression [expression] with precision [precision]
 */
fun calculateExpression(expression: String, precision: Int): String {

    assert(precision >= 0) {
        "Precision must be non-negative"
    }

    if (expression.isBlank()) return ""

    val result = DoubleEvaluator().evaluate(expression)

    return if (floor(result) == result) {
        result.toInt().toString()
    } else {
        "%.${precision}f".format(result)
    }
}