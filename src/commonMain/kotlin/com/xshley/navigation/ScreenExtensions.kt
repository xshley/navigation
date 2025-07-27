/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation

import com.xshley.navigation.routable.Routables

fun <S : Screen> ScreenStack<S>.navigate(subscreen: S): ScreenHost<S> = host.apply { navigate(subscreen) }

fun <S : Screen> ScreenStack<S>.backTo(subscreen: S, inclusive: Boolean = false): ScreenHost<S> =
    host.apply { backTo(subscreen, inclusive) }

val <S : Screen> ScreenStack<S>.routes: Routables<S>
    get() = host.routables

operator fun <S : Screen> ScreenStack<S>.invoke(screen: S): ScreenStack<S> = apply { navigate(screen) }