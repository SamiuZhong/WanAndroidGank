import com.samiu.buildsrc.BuildConfig
import com.samiu.buildsrc.Deps

plugins {
    id("com.android.application")
    kotlin("android")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Versions.composeVersion
    }
    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}

dependencies {
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.composeUi)
    implementation(Deps.AndroidX.material)
    implementation(Deps.AndroidX.material3)
    implementation(Deps.AndroidX.activityCompose)
    implementation(Deps.AndroidX.lifecycleRuntime)
    implementation(Deps.AndroidX.navigationCompose)
    implementation(Deps.AndroidX.uiToolingPreview)
    debugImplementation(Deps.AndroidX.uiTooling)
}