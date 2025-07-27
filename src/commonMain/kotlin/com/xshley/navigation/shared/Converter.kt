/*
 * Copyright (c) 2024. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.shared

fun interface Converter<S, O> {
    fun remap(source: S): O

    operator fun invoke(source: S): O = remap(source)
}