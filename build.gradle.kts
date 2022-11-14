buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}

allprojects {
    group = "com.rickclephas.kmp"
    version = "0.13.2"

    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}
