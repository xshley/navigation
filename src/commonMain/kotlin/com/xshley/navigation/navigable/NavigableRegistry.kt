/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigable

class NavigableRegistry<S : Navigable> private constructor(
    val default: S,

    private val registry: MutableMap<NavigableKey, S> = mutableMapOf(
        default.route to default
    )
) : Map<NavigableKey, S> by registry, Iterable<S> {

    constructor(
        default: S,
        routes: Iterable<S>
    ) : this(default) {
        registerAll(routes)
    }

    constructor(
        default: S,
        vararg routes: S
    ) : this(default) {
        registerAll(*routes)
    }

    private fun register(routable: S) {
        registry[routable.route] = routable
    }

    private fun registerAll(routables: Iterable<S>) {
        routables.forEach { register(it) }
    }

    private fun registerAll(vararg routables: S) = registerAll(
        routables.toList()
    )

    override fun iterator(): Iterator<S> = values.iterator()
}