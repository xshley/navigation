/*
 * Copyright (c) 2024. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.routable

class Routables<S : Routable>(
    val default: S,
    vararg others: S
) : MutableMap<String, S> by mutableMapOf(), Iterable<S> {
    init {
        register(default)
        registerAll(*others)
    }

    private fun register(routable: S): Routables<S> = apply {
        this[routable.route] = routable
    }

    private fun registerAll(vararg routables: S): Routables<S> = apply {
        routables.forEach { register(it) }
    }

    override fun iterator(): Iterator<S> = values.iterator()
}