package com.samiu.buildsrc

/**
 * @author samiu 2022/3/4
 * @email samiuzhong@outlook.com
 */
object Deps {

    object Versions {
        const val coreKtx = "1.7.0"
        const val appcompat = "1.4.0"
        const val material = "1.4.0"
        const val constraintLayout = "2.1.2"
        const val okhttp = "4.9.3"
        const val okhttpLog = "4.9.0"
        const val retrofit = "2.9.0"
        const val gson = "2.8.9"
        const val hilt = "2.40.5"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Http {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttpLog = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLog}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterGson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }
}