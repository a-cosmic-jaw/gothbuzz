import org.jetbrains.compose.ComposeExtension

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin")
    }
}

plugins {
    id("idea")
    id("org.jetbrains.kotlin.multiplatform") apply false
    id("org.jetbrains.kotlin.jvm") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("org.jetbrains.kotlin.plugin.allopen") apply false
    id("org.jetbrains.kotlin.plugin.serialization") apply false
    id("com.google.devtools.ksp") apply false
    id("com.github.johnrengelman.shadow") apply false
    id("io.micronaut.application") apply false
    id("io.micronaut.aot") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("com.varabyte.kobweb.application") apply false
    id("com.varabyte.kobwebx.markdown") apply false
    //id("com.varabyte.kobweb.library") apply false
    //id("com.varabyte.kobweb.worker") apply false
}

allprojects {
    version = "0.0.1"
    group = "eu.symmetrysought.gothbuzz"

    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
        maven("https://plugins.gradle.org/m2/")
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
        maven("https://plugins.gradle.org/m2/")
    }

//    afterEvaluate { project ->
//        if (project.hasProperty("android")) {
//            project.android {
//                if (namespace == null) {
//                    namespace project.group
//                }
//            }
//        }
//    }
    afterEvaluate {
        extensions.findByType(ComposeExtension::class.java)?.apply {
            val kotlinGeneration = project.property("kotlin.generation")
            val composeCompilerVersion = project.property("compose.compiler.version.$kotlinGeneration") as String
            kotlinCompilerPlugin.set(composeCompilerVersion)
            val kotlinVersion = project.property("kotlin.version.$kotlinGeneration") as String
            kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
        }
    }
}


