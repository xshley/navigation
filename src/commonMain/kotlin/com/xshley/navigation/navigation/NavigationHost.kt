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
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.xshley.navigation.navigable.Navigable

@Immutable
data class NavigationHost<S : Navigable>(
    val configuration: Configuration = Configuration()
) {
    val hostController: NavHostController
        get() = internalHostController

    private lateinit var internalHostController: NavHostController

    @Composable
    fun Compose(
        compose: @Composable NavigationHost<S>.() -> Unit
    ) {
        internalHostController = rememberNavController()

        compose()
    }

    @Immutable
    data class Configuration(
        val modifier: Modifier = Modifier.fillMaxSize(),
    )
}