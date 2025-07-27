/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation

import androidx.compose.runtime.Composable
import com.xshley.navigation.navigation.NavigationHostWrapper
import com.xshley.navigation.routable.Routables


abstract class ScreenStack<S : Screen>(
    val host: ScreenHost<S>,

    defaultRoute: String? = null
) : Screen(defaultRoute) {
    constructor(
        routables: Routables<S>,
        wrapper: NavigationHostWrapper<S> = NavigationHostWrapper(),
        defaultRoute: String? = null
    ) : this(
        host = ScreenHost(wrapper, routables),
        defaultRoute = defaultRoute
    )

    constructor(
        default: S,
        vararg others: S,
        wrapper: NavigationHostWrapper<S> = NavigationHostWrapper(),
        defaultRoute: String? = null
    ) : this(
        host = ScreenHost(
            default,
            *others,
            wrapper = wrapper
        ),
        defaultRoute = defaultRoute
    )

    @Composable
    override fun compose() = host.construct()
}