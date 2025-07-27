/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.routable.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.xshley.navigation.routable.AbstractRoutable

abstract class AbstractComposableRoutable(
    defaultRoute: String? = null
) : AbstractRoutable(defaultRoute), ComposableRoutable {
    override fun construct(builder: NavGraphBuilder) {
        builder.composable(
            route = this@AbstractComposableRoutable.route
        ) {
            compose()
        }
    }

    @Composable
    override fun precompose() { }

    @Composable
    override fun postcompose() { }
}
