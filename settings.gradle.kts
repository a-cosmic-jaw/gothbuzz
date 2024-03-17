
pluginManagement {
    val kspVersion: String by settings
    val shadowVersion: String by settings
    val micronautVersion: String by settings
    val androidLibraryVersion: String by settings
    val kotlinGeneration = extra["kotlin.generation"] as String
    val kotlinVersion = extra["kotlin.version.$kotlinGeneration"] as String
    val agpVersion = extra["agp.version"] as String
    val composeVersion = extra["compose.wasm.version.$kotlinGeneration"] as String
    val kobwebVersion: String by settings

    plugins {
        id("idea")
        id("kotlin") version kotlinVersion

        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jetbrains.kotlin.android") version kotlinVersion
        id("org.jetbrains.kotlin.multiplatform") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
        id("com.google.devtools.ksp") version kspVersion
        id("com.github.johnrengelman.shadow") version shadowVersion
        id("io.micronaut.application") version micronautVersion
        id("io.micronaut.aot") version micronautVersion
        id("com.android.base") version agpVersion
        id("com.android.application") version agpVersion
        id("com.android.library") version agpVersion
        id("org.jetbrains.compose") version composeVersion
        id("com.varabyte.kobweb.application") version kobwebVersion
        id("com.varabyte.kobwebx.markdown") version kobwebVersion
        //id("com.varabyte.kobweb.library") version kobwebVersion
        //id("com.varabyte.kobweb.worker") version kobwebVersion
    }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
    }
}

rootProject.name="gothbuzz"

include(":sharedBase")
include(":sharedCompose")
include(":sharedBackend")
include(":backendApi")
include(":androidApp")
include(":frontendMain")

project(":sharedBase").name = "sharedBase"
project(":sharedCompose").name = "sharedCompose"
project(":sharedBackend").name = "sharedBackend"
project(":backendApi").name = "backendApi"
project(":androidApp").name = "androidApp"
project(":frontendMain").name = "frontendMain"