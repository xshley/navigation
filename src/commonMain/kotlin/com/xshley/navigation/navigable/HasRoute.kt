/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigable

import com.xshley.navigation.helpers.nameof

interface HasRoute {
    val route: NavigableKey

    open class Concrete(
        defaultRoute: NavigableKey? = null
    ) : HasRoute {
        override val route: NavigableKey =
            defaultRoute ?: uniqueKey()
    }
}

fun HasRoute.isSimilar(
    other: HasRoute
): Boolean = isSimilar(
    other.route
)

fun HasRoute.isSimilar(
    route: NavigableKey
): Boolean = this.route == route

fun HasRoute.uniqueKey(): NavigableKey =
    nameof(this)