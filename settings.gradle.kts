rootProject.name = "WanAndroidGank"
include(":app")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version "7.2.1" apply false
        id("com.android.library") version "7.2.1" apply false
        id("org.jetbrains.kotlin.android") version "1.6.10" apply false
        id("org.jetbrains.kotlin.kapt") version "1.6.10" apply false
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}
