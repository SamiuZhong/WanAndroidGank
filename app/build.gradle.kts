import com.samiu.buildsrc.BuildConfig
import com.samiu.buildsrc.Deps

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        applicationId = BuildConfig.applicationId
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
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

    implementation(project(":base"))
    implementation(project(":res"))

    implementation(Deps.Http.X5WebView)
    implementation(Deps.Bumptech.glide)
    kapt(Deps.Bumptech.glideCompiler)
    implementation(Deps.Component.autoSize)
    implementation(Deps.Component.banner)
    implementation(Deps.Component.smartRefreshLayout)
    implementation(Deps.Component.smartRefreshHeader)
    implementation(Deps.Component.flowLayout)
    implementation(Deps.Component.materialDialog)
    implementation(Deps.Component.materialDialogLifecycle)
    implementation(Deps.Component.licensesDialog)
}
