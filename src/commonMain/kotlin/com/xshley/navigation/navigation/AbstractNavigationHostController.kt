/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.xshley.navigation.adaption.route
import com.xshley.navigation.routable.Routable
import com.xshley.navigation.routable.Routables
import com.xshley.navigation.routable.composable.ComposableRoutable
import com.xshley.navigation.routable.composable.CompositionStage
import com.xshley.navigation.routable.isRoutableTo

abstract class AbstractNavigationHostController<S : Routable>(
    override val wrapper: NavigationHostWrapper<S> = NavigationHostWrapper(),
    override val routables: Routables<S>
) : NavigationHostController<S> {
    private val backStackEntryState
        @Composable
        get() = wrapper.hostController.currentBackStackEntryAsState()

    private val backStackEntry
        @Composable
        get() = backStackEntryState.value

    constructor(
        default: S,
        vararg others: S,
        wrapper: NavigationHostWrapper<S> = NavigationHostWrapper(),
    ) : this(wrapper, Routables(default, *others))

    @Composable
    override fun construct() {
        registerPre()

        wrapper.begin {
            NavHost(
                navController = hostController,
                startDestination = routables.default.route,
                modifier = Modifier.fillMaxSize(),
                enterTransition = configuration.enterTransition,
                exitTransition = configuration.exitTransition
            ) {
                autoRoute()
            }
        }

        registerPost()
    }

    @Composable
    private fun registerPre() {
        for (routable in routables.values) {
            if (routable is ComposableRoutable) {
                routable.precompose()
            }
        }
    }

    @Composable
    private fun registerPost() {
        for (routable in routables.values) {
            if (routable is ComposableRoutable) {
                routable.postcompose()
            }
        }
    }

    override fun navigate(route: S): NavigationController<S> {
        if (isCurrentlyOnSimple(route))
            return this

        require(routables.containsKey(route.route)) {
            "No routable was registered for provided key ${route.route}"
        }

        wrapper
            .hostController
            .navigate(route.route)

        return this
    }

    override fun backTo(route: S, inclusive: Boolean): NavigationController<S> {
        if (isCurrentlyOnSimple(route))
            return this

        require(routables.containsKey(route.route)) {
            "No routable was registered for provided key ${route.route}"
        }

        wrapper
            .hostController
            .popBackStack(route.route, inclusive)

        return this
    }

    @Composable
    override fun isCurrentlyOn(route: S): Boolean =
        isCurrentlyOnSimple(route) || isCurrentlyOnBackStack(route)

    @Composable
    override fun lookupOrNull(): S? =
        lookupSimple() ?: lookupBackStack()

    private fun isCurrentlyOnSimple(routable: S): Boolean = lookupSimple()?.isRoutableTo(routable) ?: false

    @Composable
    private fun isCurrentlyOnBackStack(routable: S): Boolean = lookupBackStack()?.isRoutableTo(routable) ?: false

    private fun lookupSimple(): S? {
        val route = wrapper
            .hostController
            .currentDestination
            ?.route ?: return null

        return routables[route]
    }

    @Composable
    private fun lookupBackStack(): S? {
        val route = backStackEntry?.destination?.route
            ?: return null

        return routables[route]
    }

    private fun NavGraphBuilder.autoRoute() {
        routables.forEach { _, routable ->
            route(routable)
        }
    }
}