@file:Suppress("OPT_IN_IS_NOT_ENABLED")
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("idea")
    id("org.jetbrains.kotlin.multiplatform")
    id("com.google.devtools.ksp")
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

//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//        }
//    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/commonMain/kotlin")
            dependencies {
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
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidUnitTest by getting //TODO rename
//        val desktopMain by getting {
//            dependencies {
//                implementation(compose.desktop.common)
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
//                implementation("io.ktor:ktor-client-cio:$ktorVersion")
//            }
//        }
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
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