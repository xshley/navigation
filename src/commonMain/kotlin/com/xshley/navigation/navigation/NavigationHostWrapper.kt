/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.xshley.navigation.routable.Routable

class NavigationHostWrapper<S : Routable>(
    val configuration: Configuration = Configuration()
) {
    val hostController: NavHostController
        get() = internalHostController

    private lateinit var internalHostController: NavHostController

    @Composable
    fun begin(
        content: @Composable NavigationHostWrapper<S>.() -> Unit
    ) {
        internalHostController = rememberNavController()

        content()
    }

    @Immutable
    data class Configuration(
        val modifier: Modifier = Modifier.fillMaxSize(),

        val enterTransition:
        (@JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
            {
                fadeIn(animationSpec = tween(350))
            },

        val exitTransition:
        (@JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
            {
                fadeOut(animationSpec = tween(350))
            }
    )
}