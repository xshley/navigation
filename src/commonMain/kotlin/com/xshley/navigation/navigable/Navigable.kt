/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.xshley.navigation.navigation.NavigationAdapter

interface Navigable : NavigationAdapter, HasRoute {

    interface Composable : Navigable {
        @androidx.compose.runtime.Composable
        fun PreCompose() { }

        @androidx.compose.runtime.Composable
        fun Compose()

        @androidx.compose.runtime.Composable
        fun PostCompose() { }
    }

    abstract class AbstractComposable(
        defaultRoute: NavigableKey? = null
    ) : Abstract(defaultRoute), Composable {
        override fun adapt(source: NavGraphBuilder) {
            source.composable(
                route = this.route
            ) { Compose() }
        }
    }

    abstract class Abstract(
        defaultRoute: NavigableKey? = null
    ) : HasRoute.Concrete(defaultRoute), Navigable
}

fun Navigable.isComposable(): Boolean = this is Navigable.Composable