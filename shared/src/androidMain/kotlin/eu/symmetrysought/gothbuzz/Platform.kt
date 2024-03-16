package eu.symmetrysought.gothbuzz

class AndroidPlatform : Platform {
    override val name: String = "JVM 17"
}

actual fun getPlatform(): Platform = AndroidPlatform()