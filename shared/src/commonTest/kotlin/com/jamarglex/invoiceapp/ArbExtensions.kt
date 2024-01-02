package com.jamarglex.invoiceapp

import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.element
import io.kotest.property.arbitrary.take

fun <T> Arb<T>.genList(
    amount: Int = 10,
    randomSource: RandomSource = RandomSource.default()
): List<T> = take(amount, randomSource).toList()

fun <T> Arb<T>.gen(randomSource: RandomSource = RandomSource.default()): T =
    genList(1, randomSource).first()

fun <T> Arb<T>.genOrNull(randomSource: RandomSource = RandomSource.default()): T? =
    Arb.element(null, genList(1, randomSource).first()).gen()
