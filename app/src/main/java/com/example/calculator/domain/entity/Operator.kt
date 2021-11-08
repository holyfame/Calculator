package com.example.calculator.domain.entity

enum class Operator(val symbol: String) {
    MINUS("-"),
    PLUS("+"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POINT("."),
    POWER("^"),
    LEFT_BRACE("("),
    RIGHT_BRACE(")")
}