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

package com.xshley.framework.shared.helpers

import com.xshley.navigation.helpers.nameof

interface Typecast<T>

inline fun <reified T, reified S : Typecast<T>> Typecast<*>.cast(): S = this as S

inline fun <reified S> Any?.cast(): S = this as S

inline fun <reified S> Any?.castOrNull(): S? = this as? S

inline fun <reified S : Any> Any?.tryCast(): S =
    requireNotNull(tryCastOrNull()) { "Could not cast from ${nameof(this)}"}

inline fun <reified S : Any> Any?.tryCastOrNull(): S? {
    var got: S? = null

    try {
        got = this.cast()
    } catch (_: Exception) { /* Ignored */ }

    return got
}