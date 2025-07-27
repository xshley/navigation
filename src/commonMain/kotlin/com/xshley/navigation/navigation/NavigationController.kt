/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.xshley.framework.shared.helpers.tryCast
import com.xshley.navigation.collections.stack.MutableStack
import com.xshley.navigation.helpers.Predicate
import com.xshley.navigation.helpers.meets
import com.xshley.navigation.helpers.predicate
import com.xshley.navigation.navigable.Navigable
import com.xshley.navigation.navigable.NavigableRegistry
import com.xshley.navigation.navigable.isComposable
import com.xshley.navigation.navigable.isSimilar
import kotlin.collections.get

open class NavigationController<S : Navigable>(
    val controller: NavigationHost<S> = NavigationHost(),

    registry: NavigableRegistry<S>
) : Navigation<S>(registry) {
    private object Preconditions {
        fun <S : Navigable> isComposable(): Predicate<S> = predicate { it.isComposable() }
    }

    override val size: Int
        get() = backingSize

    private var backingSize: Int = 1

    override val capacity: Int = -1

    constructor(
        default: S,
        vararg routes: S,

        controller: NavigationHost<S> = NavigationHost()
    ) : this(
        controller,
        NavigableRegistry(
            default,
            *routes
        )
    )

    @Composable
    fun Compose() {
        applyTo<Navigable.Composable> { PreCompose() }

        controller.Compose {
            NavHost(
                navController = hostController,
                startDestination = registry
                    .default
                    .route,

                modifier = Modifier.fillMaxSize(),
            ) {
                for (navigable in registry)
                    navigable.adapt(this)
            }
        }

        applyTo<Navigable.Composable> { PostCompose() }
    }

    @Composable
    private inline fun <reified SubSource : Navigable> applyTo(
        precondition: Predicate<S> = Preconditions.isComposable(),
        block: @Composable SubSource.() -> Unit
    ) {
        for (navigable in registry) {
            if (!(navigable meets precondition))
                continue

            val got = navigable.tryCast<SubSource>()

            block(got)
        }
    }

    @Composable
    override fun destinationOrNull(): S? =
        lookupSimple() ?: lookupBackStack()

    @Composable
    override fun isCurrentlyOn(route: S): Boolean =
        isCurrentlyOnSimple(route) || isCurrentlyOnBackStack(route)

    override fun push(element: S): MutableStack<S> = apply {
        if (isCurrentlyOnSimple(element))
            return this

        require(registry.containsKey(element.route)) {
            "No routable was registered for provided key ${element.route}"
        }

        controller
            .hostController
            .navigate(element.route)

        backingSize++
    }

    override fun pop(): S {
        controller
            .hostController
            .popBackStack()

        val entry = controller
            .hostController
            .currentDestination



        return requireNotNull(registry[
                entry?.route
        ])
    }

    override fun peek(): S {
        val stack = controller
            .hostController
            .currentBackStack
            .value

        return requireNotNull(registry[
            stack[stack.lastIndex - 1].id
        ])
    }

    override fun iterator(): Iterator<S> {
        val stack = controller
            .hostController
            .currentBackStack
            .value

        val elements: MutableList<S> = mutableListOf()

        for (element in stack) {
            elements.add(
                requireNotNull(registry[
                    element.id
                ])
            )
        }

        return elements.iterator()
    }

    override fun clear(): MutableStack<S> = apply {
        controller
            .hostController
            .clearBackStack<Any>()
    }

    private fun isCurrentlyOnSimple(navigable: S): Boolean =
        lookupSimple()?.isSimilar(navigable) ?: false

    @Composable
    private fun isCurrentlyOnBackStack(navigable: S): Boolean =
        lookupBackStack()?.isSimilar(navigable) ?: false

    private fun lookupSimple(): S? {
        val route = controller
            .hostController
            .currentDestination
            ?.route ?: return null

        return registry[route]
    }

    @Composable
    private fun lookupBackStack(): S? {
        val route = backStackEntry()?.destination?.route
            ?: return null

        return registry[route]
    }
    @Composable
    private fun backStackEntryState(): State<NavBackStackEntry?> = controller
        .hostController
        .currentBackStackEntryAsState()

    @Composable
    private fun backStackEntry(): NavBackStackEntry? = backStackEntryState()
        .value
}