package com.samiu.buildsrc

/**
 * @author samiu 2022/3/4
 * @email samiuzhong@outlook.com
 */
object Deps {

    object Versions {
        const val coreKtx = "1.7.0"
        const val composeVersion = "1.2.0-alpha07"
        const val material3 = "1.0.0-alpha09"
        const val activityCompose = "1.4.0"
        const val lifecycle = "2.4.1"
        const val navigation = "2.4.2"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val material =
            "androidx.compose.material:material:${Versions.composeVersion}"
        const val material3 =
            "androidx.compose.material3:material3:${Versions.material3}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val uiTooling =
            "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
        const val uiToolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
        const val navigationCompose =
            "androidx.navigation:navigation-compose:${Versions.navigation}"
    }
}