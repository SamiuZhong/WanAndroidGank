plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

val coreKtxVersion: String by project
val composeVersion: String by project
val material3Version: String by project
val activityComposeVersion: String by project
val lifecycleVersion: String by project
val navigationComposeVersion: String by project
val recyclerviewVersion: String by project
val loggingInterceptorVersion: String by project
val retrofitVersion: String by project
val viewModelComposeVersion: String by project
val hiltVersion: String by project
val hiltComposeVersion: String by project
val hiltCompilerVersion: String by project
val pagingVersion: String by project
val coilVersion: String by project
val roomVersion: String by project

android {
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
            add("META-INF/INDEX.LIST")
        }
    }
}

kapt {
    correctErrorTypes = true
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

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$viewModelComposeVersion")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:$navigationComposeVersion")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
//    kapt("androidx.hilt:hilt-compiler:$hiltCompilerVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltComposeVersion")

    // Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")

    // Paging3
    implementation("androidx.paging:paging-compose:$pagingVersion")

    // Coil
    implementation("io.coil-kt:coil-compose:$coilVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
}