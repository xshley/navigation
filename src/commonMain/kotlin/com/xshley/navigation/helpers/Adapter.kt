/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.helpers

fun interface Adapter<S> {
    fun adapt(source: S)
}

fun <S> Adapter<S>.apply(source: S, block: Adapter<S>.() -> Unit = { }): Adapter<S> = apply {
    adapt(source)
}

fun <S> S.adapt(adapter: Adapter<S>) = adapter(this)

operator fun <S> Adapter<S>.invoke(source: S) = adapt(source)