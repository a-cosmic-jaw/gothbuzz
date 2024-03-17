package eu.symmetrysought.gothbuzz

class JvmPlatform : Platform {
    override val name: String = "JVM 17"
}

actual fun getPlatform(): Platform = JvmPlatform()