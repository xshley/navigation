/*
 * Copyright (c) 2025. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.navigation

import com.xshley.navigation.navigable.Navigable
import com.xshley.navigation.navigable.NavigableRegistry
import com.xshley.navigation.navigable.NavigableStack

abstract class Navigation<S : Navigable>(
    val registry: NavigableRegistry<S>
) : NavigableStack<S>, HasDestination<S>