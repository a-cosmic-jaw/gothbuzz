@file:Suppress("OPT_IN_IS_NOT_ENABLED")
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("idea")
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}

val ktorVersion = "2.2.1"//: String by project

kotlin {
    //jvm("desktop")
    // https://stackoverflow.com/questions/76108428/how-do-i-fix-namespace-not-specified-error-in-android-studio
    androidTarget {
        //namespace("eu.symmetrysought.gothbuzz")

        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":sharedBase"))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":sharedBase"))
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":sharedBase"))

                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidUnitTest by getting
            dependencies {
                implementation(project(":sharedBase"))
            }
    }
}

android {
    namespace = "example.imageviewer.shared"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    defaultConfig {
        minSdk = 31
    }

    // TODO: This is a bug in linter actually, this needs to be investigated and reported
    lint {
        disable.addAll(setOf("ModifierFactoryExtensionFunction", "ModifierFactoryReturnType", "ModifierFactoryUnreferencedReceiver"))
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }

    buildFeatures {
        buildConfig = false
    }
    kotlin {
        jvmToolchain(17)
    }
}
