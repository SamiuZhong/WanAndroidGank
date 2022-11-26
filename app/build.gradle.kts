plugins {
    id("com.android.application")
    kotlin("android")
}

val coreKtxVersion: String by project
val composeVersion: String by project
val material3Version: String by project
val activityComposeVersion: String by project
val lifecycleVersion: String by project
val navigationVersion: String by project
val recyclerviewVersion: String by project
val adaptiveVersion: String by project

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.samiu.wangank"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.material3:material3:$material3Version")
    implementation("androidx.recyclerview:recyclerview:$recyclerviewVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.navigation:navigation-compose:$navigationVersion")
}