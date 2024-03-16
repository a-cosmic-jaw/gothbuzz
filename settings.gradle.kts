
pluginManagement {
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    val shadowVersion: String by settings
    val micronautVersion: String by settings

    plugins {
        id("org.jetbrains.kotlin.multiplatform") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
        id("com.google.devtools.ksp") version kspVersion
        id("com.github.johnrengelman.shadow") version shadowVersion
        id("io.micronaut.application") version micronautVersion
        id("io.micronaut.aot") version micronautVersion
    }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name="gothbuzz"

include(":backendApi")
