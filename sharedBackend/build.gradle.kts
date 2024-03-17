plugins {
    id("idea")
    id("org.jetbrains.kotlin.jvm")
}

val kotlinGeneration = extra["kotlin.generation"] as String
val kotlinVersion = extra["kotlin.version.$kotlinGeneration"] as String


dependencies {
    implementation(project(":sharedBase"))
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
}
