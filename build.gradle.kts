
plugins {
    id("org.jetbrains.kotlin.multiplatform") apply false
    id("org.jetbrains.kotlin.plugin.allopen") apply false
    id("org.jetbrains.kotlin.plugin.serialization") apply false
    id("com.google.devtools.ksp") apply false
    id("com.github.johnrengelman.shadow") apply false
    id("io.micronaut.application") apply false
    id("io.micronaut.aot") apply false
}

version = "0.0.1"
group = "eu.symmetrysought.gothbuzz"

subprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
    }
}


