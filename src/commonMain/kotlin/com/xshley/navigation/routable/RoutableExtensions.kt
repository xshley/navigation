/*
 * Copyright (c) 2024-2025. Ashley <ax-ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

package com.xshley.navigation.routable

import com.xshley.navigation.shared.string.StringConverter
import com.xshley.navigation.shared.string.StringConverters
import kotlin.reflect.KClass
import kotlin.reflect.full.cast
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.superclasses


@Internal
object RoutableConverter : StringConverter<KClass<*>> {
    private val BackingConverter: StringConverter<KClass<*>> = StringConverters.Class()

    override fun remap(source: KClass<*>): String =
        BackingConverter(source).replace("Screen", "")
}

@OptIn(Internal::class)
inline val <reified S : Routable, reified C : S> C.inheritence: List<S>
    get() = this::class.superclasses
        .filter { it.isSubclassOf(S::class) }
        .filter { it != this::class && RoutableConverter(it::class) != RoutableConverter(this::class) }
        .map { it.cast(this) as S }

fun <S : Routable> S.isRoutableTo(other: S): Boolean = this.route == other.route

