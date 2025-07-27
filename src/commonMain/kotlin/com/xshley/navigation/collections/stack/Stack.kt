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

import com.xshley.navigation.collections.HasStorage
import com.xshley.navigation.collections.stack.MutableStack.Concrete

interface Stack<S> : Iterable<S>, HasStorage {
    companion object {
        fun <S> immutable(
            vararg elements: S,
            capacity: Int = elements.size
        ): Stack<S> = Concrete(*elements, capacity = capacity)

        fun <S> mutable(
            vararg elements: S,
            capacity: Int = elements.size
        ): MutableStack<S> = MutableStack(*elements, capacity = capacity)
    }

    fun peek(): S

    open class Concrete<S>(
        elements: List<S>,

        override val capacity: Int = elements.size,
    ) : Stack<S> {
        protected val elements: MutableList<S> = elements.toMutableList()

        override val size: Int
            get() = elements.size

        constructor(
            vararg elements: S,
        ) : this(listOf(*elements))

        override fun peek(): S =
            elements.first()

        override fun iterator(): Iterator<S> = elements.iterator()
    }
}