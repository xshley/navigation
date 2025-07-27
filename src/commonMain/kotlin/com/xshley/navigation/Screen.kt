/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation

import com.xshley.navigation.routable.composable.AbstractComposableRoutable

abstract class Screen(defaultRoute: String? = null) : AbstractComposableRoutable(defaultRoute)