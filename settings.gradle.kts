
pluginManagement {
    val kspVersion: String by settings
    val shadowVersion: String by settings
    val micronautVersion: String by settings
    val androidLibraryVersion: String by settings
    val kotlinGeneration = extra["kotlin.generation"] as String
    val kotlinVersion = extra["kotlin.version.$kotlinGeneration"] as String
    val agpVersion = extra["agp.version"] as String
    val composeVersion = extra["compose.wasm.version.$kotlinGeneration"] as String

    plugins {
        id("idea")
        id("kotlin")
        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)

        kotlin("android").version(kotlinVersion)
        id("org.jetbrains.kotlin.multiplatform") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
        id("com.google.devtools.ksp") version kspVersion
        id("com.github.johnrengelman.shadow") version shadowVersion
        id("io.micronaut.application") version micronautVersion
        id("io.micronaut.aot") version micronautVersion
        id("com.android.base").version(agpVersion)
        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)
    }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name="gothbuzz"

include(":sharedCompose")
include(":backendApi")
include(":androidApp")

project(":sharedCompose").name = "sharedCompose"
project(":backendApi").name = "backendApi"
project(":androidApp").name = "androidApp"