/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation

import com.xshley.navigation.navigation.AbstractNavigationHostController
import com.xshley.navigation.navigation.NavigationHostWrapper
import com.xshley.navigation.routable.Routables

open class ScreenHost<S : Screen> : AbstractNavigationHostController<S> {
    constructor(
        wrapper: NavigationHostWrapper<S> = NavigationHostWrapper(),
        routables: Routables<S>
    ) : super(wrapper, routables)

    constructor(default: S, vararg others: S, wrapper: NavigationHostWrapper<S> = NavigationHostWrapper()) : super(
        default,
        *others,
        wrapper = wrapper
    )
}
