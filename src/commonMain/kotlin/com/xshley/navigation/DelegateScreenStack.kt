/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation

import androidx.compose.runtime.Composable
import com.xshley.navigation.navigable.NavigableRegistry
import com.xshley.navigation.navigation.NavigationHost

open class DelegateScreenStack<S : Screen> : ScreenStack<S> {
    private var delegate: ComposableView

    constructor(
        delegate: ComposableView,

        routables: NavigableRegistry<S>,
        controller: NavigationHost<S>
    ) : super(routables, controller) { this.delegate = delegate }

    constructor(
        delegate: ComposableView,

        default: S,
        vararg others: S,
        controller: NavigationHost<S> = NavigationHost()
    ) : super(default, *others, controller = controller ) { this.delegate = delegate }

    @Composable
    override fun Compose() = delegate()
}