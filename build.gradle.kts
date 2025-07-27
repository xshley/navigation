/*
 * Copyright (c) 2024. Ashley <xshley>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 */

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)

    id("maven-publish")
}

group = "com.xshley.navigation"
version = "1.0.0"

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(libs.navigation.compose)

            implementation(kotlin("reflect"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("jitpack") {
            from(components["kotlin"])

            groupId = "com.xshley.navigation"
            artifactId = "navigation"
        }
    }
}