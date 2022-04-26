plugins {
    id("com.android.application") version "7.2.0-rc01" apply false
    id("com.android.library") version "7.2.0-rc01" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
//    id("org.jetbrains.kotlin.kapt") version "1.6.10" apply false
}

tasks.register<Delete>(name = "clean") {
    group = "build"
    delete(rootProject.buildDir)
}