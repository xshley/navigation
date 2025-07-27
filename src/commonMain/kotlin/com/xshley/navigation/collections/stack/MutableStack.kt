/*
 * Copyright (c) 2025 Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * This file includes elements of the original Apache 2.0 License.
 *
 * Do:
 *   - Adhere strictly to the terms and conditions of the Apache License.
 *   - Provide proper attribution to the original Apache License in derivative works.
 *   - Document and note any modifications made to the original code.
 *
 * Do Not:
 *   - Remove or modify this license header in any distributed copies.
 *   - Use the code in a manner that violates or undermines the original license terms.
 */

package com.xshley.navigation.collections.stack

import com.xshley.navigation.collections.isNotFull
import com.xshley.navigation.helpers.nameof

interface MutableStack<S> : Stack<S> {
    companion object {
        operator fun <S> invoke(
            vararg elements: S,
            capacity: Int = elements.size
        ): MutableStack<S> = Concrete(*elements, capacity = capacity)
    }

    fun push(element: S): MutableStack<S>

    fun pop(): S

    fun clear(): MutableStack<S>

    open class Concrete<S>(
        elements: List<S> = listOf(),

        capacity: Int = elements.size
    ) : Stack.Concrete<S>(elements, capacity), MutableStack<S> {
        constructor(
            vararg elements: S,

            capacity: Int = elements.size
        ) : this(
            listOf(*elements),
            capacity
        )

        override fun push(element: S): MutableStack<S> = apply {
            require(isNotFull()) { "${nameof(this)} is full, size: $size, capacity: $capacity" }

            this.elements.add(element)
        }

        override fun pop(): S {
            val result = peek()

            elements.removeLast()

            return result
        }

        override fun clear(): MutableStack<S> = apply {
            elements.clear()
        }
    }
}

fun <S> MutableStack<S>.pushAll(vararg elements: S): MutableStack<S> = apply {
    elements.forEach { push(it) }
}

fun <S> MutableStack<S>.popTo(target: S): MutableStack<S> = apply {
    while (pop() != target)
    { }
}

fun <S> MutableStack<S>.pop(n: Int): List<S> {
    require(n >= 0) { "Count must be non-negative" }

    return List(n.coerceAtMost(size)) { pop() }
}