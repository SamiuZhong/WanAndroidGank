import com.samiu.buildsrc.BuildConfig
import com.samiu.buildsrc.Deps

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk
    }

    compileOptions {
        sourceCompatibility = BuildConfig.javaVersion
        targetCompatibility = BuildConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = BuildConfig.kotlinJvmTarget
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}


dependencies {
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appcompat)

    //AndroidX、Jetpack
    api("androidx.viewpager2:viewpager2:1.0.0")
    api("androidx.constraintlayout:constraintlayout:2.1.3")
    api("androidx.navigation:navigation-ui-ktx:2.4.1")
    api("androidx.navigation:navigation-fragment-ktx:2.4.1")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    api("androidx.paging:paging-runtime-ktx:3.1.0")

    //Kotlin Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    //Koin注入框架
    api("io.insert-koin:koin-core:3.1.2")
    api("io.insert-koin:koin-android:3.1.2")
    api("io.insert-koin:koin-android-compat:3.1.2")

    //网络
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")
}
