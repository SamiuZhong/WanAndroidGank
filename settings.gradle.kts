pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version "7.1.0" apply false
        id("com.android.library") version "7.1.0" apply false
        id("org.jetbrains.kotlin.android") version "1.6.0" apply false
        id("org.jetbrains.kotlin.kapt") version "1.6.0" apply false
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
        maven {
            setUrl("https://dl.bintray.com/thelasterstar/maven/")
        }
    }
}

rootProject.name = "WanAndroid_Gank"
include(":app")
include(":base")
include(":res")
