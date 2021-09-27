package com.example.calculator

import java.lang.Math.PI
import kotlin.math.pow

fun main() {
    val circle = Circle(1.2)
    val rect = Rectangle(1.0, 2.0)
    val square = Square(2.0)

    val figures: List<Figure> = listOf(circle, rect, square)
    for (fig in figures) {
        println("${fig.type}, ${fig.perimeter}, ${fig.area}")
    }

    // biggest perimeter
    println((figures.maxByOrNull{ it.perimeter })?.type)

    // biggest radius
    println((figures.maxByOrNull { it.area })?.type)
}

interface Figure {
    val perimeter: Double
    val area: Double

    val type: String
}

class Circle(
    private val radius: Double
) : Figure {

    override val perimeter: Double
        get() = 2 * PI * radius

    override val area: Double
        get() = PI * radius.pow(2)

    override val type: String = "Circle"

}

open class Rectangle(
    private val side1: Double,
    private val side2: Double
) : Figure {

    override val perimeter: Double
        get() = side1 * 2 + side2 * 2

    override val area: Double
        get() = side1 * side2

    override val type: String = "Rectangle"

}

class Square(
    side: Double
) : Rectangle(side, side) {

    override val type: String = "Square"

}