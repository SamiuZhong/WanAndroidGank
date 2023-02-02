import com.samiu.buildsrc.BuildConfig
import com.samiu.buildsrc.Deps

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk
    }
}

dependencies {
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appcompat)

    api(Deps.Google.material)
}
