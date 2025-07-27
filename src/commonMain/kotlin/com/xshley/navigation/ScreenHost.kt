/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation

import com.xshley.navigation.navigable.NavigableRegistry
import com.xshley.navigation.navigation.NavigationController
import com.xshley.navigation.navigation.NavigationHost

open class ScreenHost<S : Screen> : NavigationController<S> {
    constructor(
        wrapper: NavigationHost<S> = NavigationHost(),
        routes: NavigableRegistry<S>
    ) : super(wrapper, routes)

    constructor(
        default: S,
        vararg others: S,
        host: NavigationHost<S> = NavigationHost()
    ) : super(
        default,
        *others,
        controller = host
    )
}
