/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation

import androidx.compose.runtime.Composable
import com.xshley.navigation.navigable.NavigableKey

typealias ComposableView = @Composable Screen.() -> Unit

open class DelegateScreen(
    private val delegate: ComposableView,

    defaultRoute: NavigableKey? = null
) : Screen(defaultRoute) {

    @Composable
    override fun Compose() = delegate()
}