import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

val keystoreProperties = Properties()
val keystorePropertiesFile = rootProject.file("keystore.properties")
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

@Suppress("UnstableApiUsage")
android {
    namespace = "com.samiu.wangank"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.samiu.wangank"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

kapt {
    correctErrorTypes = true
}

// library versions are in PROJECT_ROOT/gradle/libs.versions.toml
dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.navigation)
    implementation(libs.android.material)
    implementation(libs.paging.runtime)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)
    implementation(libs.lifecycle.viewmodel)
    kapt(libs.lifecycle.compiler)

    implementation(libs.bundles.retrofit)
    implementation(libs.youth.banner)
    implementation(libs.coil.kt)
}