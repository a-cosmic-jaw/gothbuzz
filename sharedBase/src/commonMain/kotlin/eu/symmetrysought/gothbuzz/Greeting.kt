package eu.symmetrysought.gothbuzz

//import eu.symmetrysought.gothbuzz.ksp.ExampleGenerated

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val generatedGreeting = "statisk string"//ExampleGenerated.generatedMessage()
        return "Hello, ${platform.name}! Big common things follow: $generatedGreeting"
    }
}