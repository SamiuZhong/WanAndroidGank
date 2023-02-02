tasks.register<Delete>(name = "clean") {
    group = "build"
    delete(rootProject.buildDir)
}

//buildscript {
//    dependencies {
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
//    }
//}

plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.21" apply false
    id("org.jetbrains.kotlin.kapt") version "1.7.21" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
