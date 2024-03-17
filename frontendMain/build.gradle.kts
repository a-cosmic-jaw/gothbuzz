import com.varabyte.kobweb.gradle.application.extensions.AppBlock.LegacyRouteRedirectStrategy
import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("com.varabyte.kobweb.application")
    id("com.varabyte.kobwebx.markdown")
    //id("com.varabyte.kobweb.library")
    //id("com.varabyte.kobweb.worker")
}

val kobwebVersion: String by project

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }

        // Only legacy sites need this set. Sites built after 0.16.0 should default to DISALLOW.
        // See https://github.com/varabyte/kobweb#legacy-routes for more information.
        legacyRouteRedirectStrategy.set(LegacyRouteRedirectStrategy.DISALLOW)
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("kobwebagain" /*, includeServer = true*/)

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation("com.varabyte.kobweb:kobweb-core:$kobwebVersion")
                implementation("com.varabyte.kobweb:kobweb-silk:$kobwebVersion")
                implementation("com.varabyte.kobwebx:kobwebx-markdown:$kobwebVersion")
                //implementation("com.varabyte.kobweb:kobweb-api:$kobwebVersion")
                implementation("com.varabyte.kobweb:kobweb-worker:$kobwebVersion")
                implementation("com.varabyte.kobweb:silk-foundation:$kobwebVersion")
                implementation("com.varabyte.kobwebx:silk-icons-fa:$kobwebVersion")
                implementation("com.varabyte.kobwebx:silk-icons-mdi:$kobwebVersion")
                // This default template uses built-in SVG icons, but what's available is limited.
                // Uncomment the following if you want access to a large set of font-awesome icons:
                // implementation("com.varabyte.kobwebx:silk-icons-fa:$kobwebVersion")
            }
        }
    }
}
