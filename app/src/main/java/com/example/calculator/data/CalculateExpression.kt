package com.example.calculator.domain

import com.fathzer.soft.javaluator.DoubleEvaluator
import java.lang.StringBuilder
import kotlin.math.exp
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

fun main() {
    var str: String = "555"
    val stb = StringBuilder(str)
    stb.deleteAt(2)
    str = stb.toString()
    println(str)
}