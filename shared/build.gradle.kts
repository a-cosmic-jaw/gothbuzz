@file:Suppress("OPT_IN_IS_NOT_ENABLED")
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("idea")
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}


kotlin {
    jvm()
    // https://stackoverflow.com/questions/76108428/how-do-i-fix-namespace-not-specified-error-in-android-studio
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    sourceSets {
        val commonMain by getting {

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {

            }
        }
        val androidUnitTest by getting //TODO rename

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