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

    //依赖工程组件
    implementation(project(":base"))
    implementation(project(":res"))

    //WebView
    implementation("com.tencent.tbs.tbssdk:sdk:43697")
    //Glide图片框架
    implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")
    //屏幕适配
    implementation("me.jessyan:autosize:1.2.1")
    //Banner
    implementation("com.youth.banner:banner:2.0.0-alpha02")
    //下拉刷新
    implementation("com.scwang.smartrefresh:SmartRefreshLayout:1.1.0")
    implementation("com.scwang.smartrefresh:SmartRefreshHeader:1.1.0")
    //FlowLayout
    implementation("com.nex3z:flow-layout:1.3.1")
    //dialog
    implementation("com.afollestad.material-dialogs:core:3.3.0")
    implementation("com.afollestad.material-dialogs:lifecycle:3.3.0")
    implementation("de.psdev.licensesdialog:licensesdialog:2.1.0")
}
