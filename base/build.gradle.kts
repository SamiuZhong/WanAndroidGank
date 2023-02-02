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

    api(Deps.AndroidX.viewpager2)
    api(Deps.AndroidX.constraintLayout)
    api(Deps.AndroidX.navigationUi)
    api(Deps.AndroidX.navigationFragment)
    api(Deps.AndroidX.lifecycleRuntime)
    api(Deps.AndroidX.lifecycleLivedata)
    api(Deps.AndroidX.lifecycleViewModel)
    api(Deps.AndroidX.swipeRefresh)
    api(Deps.AndroidX.pagingRuntime)

    api(Deps.Kotlin.kotlinxCoroutines)
    api(Deps.Koin.core)
    api(Deps.Koin.android)
    api(Deps.Koin.compat)
    api(Deps.Http.retrofit)
    api(Deps.Http.retrofitConverter)
    api(Deps.Http.okHttp)
    implementation(Deps.Http.cookieJar)
}
