
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin")
    }
}

plugins {
    id("idea")
    id("org.jetbrains.kotlin.multiplatform") apply false
    id("org.jetbrains.kotlin.plugin.allopen") apply false
    id("org.jetbrains.kotlin.plugin.serialization") apply false
    id("com.google.devtools.ksp") apply false
    id("com.github.johnrengelman.shadow") apply false
    id("io.micronaut.application") apply false
    id("io.micronaut.aot") apply false
    id("com.android.library") apply false
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
}


