/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigation

import androidx.compose.runtime.Composable
import com.xshley.navigation.navigable.Navigable

interface HasDestination<S : Navigable> {
    @Composable
    fun destination(): S = requireNotNull(
        destinationOrNull()
    ) { "Destination was null" }

    @Composable
    fun destinationOrNull(): S?

    @Composable
    fun isCurrentlyOn(route: S): Boolean
}

val <S : Navigable> HasDestination<S>.destination: S
    @Composable get() = destination()

val <S : Navigable> HasDestination<S>.nullableDestination: S?
    @Composable get() = destinationOrNull()