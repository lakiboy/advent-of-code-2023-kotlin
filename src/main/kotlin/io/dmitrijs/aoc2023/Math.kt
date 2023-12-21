package io.dmitrijs.aoc2023

fun lcm(items: List<Long>) = items.reduce { lcm, num -> lcm(lcm, num) }

fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)

fun gcd(a: Long, b: Long): Long {
    var (num1, num2) = a to b

    while (num2 != 0L)
        num2 = (num1 % num2).also { num1 = num2 }

    return num1
}
