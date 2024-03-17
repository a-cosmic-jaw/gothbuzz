@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("idea")
    id("org.jetbrains.kotlin.multiplatform")
}

val kspVersion: String by project

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
            }
            kotlin.srcDir("src/jvmMain/kotlin")
            resources.srcDir("src/jvmMain/resources")
        }
    }
}