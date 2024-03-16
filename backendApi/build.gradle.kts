
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
    id("io.micronaut.aot")

    //https://www.kotlinsos.com/community-libraries/how-to-set-manifests-attributes-in-gradle-8-x-and-kotlin-dsl/
    //id("org.gradle.manifest")
}

val kotlinVersion by project.properties

kotlin {
    jvmToolchain(17)
    jvm {
        withJava()
    }
    sourceSets {
        jvmMain.dependencies {
            //ksp("io.micronaut:micronaut-http-validation")
            //ksp("io.micronaut.serde:micronaut-serde-processor")
            //implementation(kotlin("reflect"))
            //implementation("io.micronaut:micronaut-inject")
            implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
            implementation("io.micronaut.serde:micronaut-serde-jackson")
            implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
            compileOnly("io.micronaut:micronaut-http-client")
            runtimeOnly("ch.qos.logback:logback-classic")
            runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))

                implementation("org.junit.jupiter:junit-jupiter-api")
                implementation("io.micronaut.test:micronaut-test-junit5")
                implementation("org.junit.jupiter:junit-jupiter-engine")
                implementation("io.micronaut:micronaut-http-client")
            }
        }
    }
}

allOpen {
    annotation("io.micronaut.aop.Around")
}

dependencies {
    // https://medium.com/@jacobras/using-ksp-with-kotlin-multiplatform-a-quick-overview-6b858df77b5f
//    add("jvmTest", "io.micronaut:micronaut-http-validation")

    //ksp("io.micronaut:micronaut-http-validation")
    //ksp("io.micronaut.serde:micronaut-serde-processor")
}

application {
    mainClass.set("eu.symmetrysought.gothbuzz.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("eu.symmetrysought.gothbuzz.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}
