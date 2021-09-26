package com.example.calculator

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    var sum: Int = 0
    for (i in 1..n) {
        val temp = scanner.nextInt()
        sum += temp
    }
    println("sum is $sum")
}