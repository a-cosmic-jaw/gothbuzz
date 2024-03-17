package eu.symmetrysought.gothbuzz

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform