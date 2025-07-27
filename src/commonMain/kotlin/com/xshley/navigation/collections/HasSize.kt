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

package com.xshley.navigation.collections

private const val ZERO: Int = 0

interface HasSize {
    val size: Int
}

fun HasSize.isEmpty(): Boolean = size == ZERO

fun HasSize.isNotEmpty(): Boolean = !isEmpty()

fun HasSize.check(
    index: Int
): Boolean = index in 0..size