package com.samiu.buildsrc

import org.gradle.api.JavaVersion

/**
 * @author samiu 2022/3/4
 * @email samiuzhong@outlook.com
 */
object BuildConfig {

    val javaVersion = JavaVersion.VERSION_11
    const val kotlinJvmTarget = "11"

    const val applicationId = "com.samiu.wangank"
    const val compileSdk = 32
    const val minSdk = 24
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
}