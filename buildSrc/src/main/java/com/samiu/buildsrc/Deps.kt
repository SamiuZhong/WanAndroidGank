package com.samiu.buildsrc

/**
 * @author samiu 2022/3/4
 * @email samiuzhong@outlook.com
 */
object Deps {

    object Versions {
        const val coreKtx = "1.7.0"
        const val appcompat = "1.4.0"
        const val viewpager2 = "1.0.0"
        const val constraintLayout = "2.1.3"
        const val navigation = "2.4.1"
        const val lifecycle = "2.4.1"
        const val swipeRefresh = "1.1.0"
        const val paging = "3.1.0"
        const val kotlinxCoroutines = "1.5.0"
        const val koin = "3.1.2"
        const val retrofit = "2.9.0"
        const val okHttp = "4.9.3"
        const val cookieJar = "v1.0.1"
        const val material = "1.4.0-alpha02"
        const val X5WebView = "43697"
        const val glide = "4.11.0"
        const val autoSize = "1.2.1"
        const val banner = "2.0.0-alpha02"
        const val smartRefresh = "1.1.0"
        const val flowLayout = "1.3.1"
        const val materialDialog = "3.3.0"
        const val licensesDialog = "2.1.0"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val lifecycleLivedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val swipeRefresh =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
        const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    }

    object Kotlin {
        const val kotlinxCoroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
    }

    object Google {
        //material design
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    /**
     * Koin inject
     */
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compat = "io.insert-koin:koin-android-compat:${Versions.koin}"
    }

    /**
     * Network component
     */
    object Http {
        //Tencent WebView
        const val X5WebView = "com.tencent.tbs.tbssdk:sdk:${Versions.X5WebView}"

        //Retrofit,OkHttp
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

        //cookie jar
        const val cookieJar = "com.github.franmontiel:PersistentCookieJar:${Versions.cookieJar}"
    }

    /**
     * Glide
     */
    object Bumptech {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    /**
     * Other component
     */
    object Component {
        //autosize screen adaptation
        const val autoSize = "me.jessyan:autosize:${Versions.autoSize}"

        //banner
        const val banner = "com.youth.banner:banner:${Versions.banner}"

        //smart refresh layout
        const val smartRefreshLayout =
            "com.scwang.smartrefresh:SmartRefreshLayout:${Versions.smartRefresh}"
        const val smartRefreshHeader =
            "com.scwang.smartrefresh:SmartRefreshHeader:${Versions.smartRefresh}"

        //flow layout
        const val flowLayout = "com.nex3z:flow-layout:${Versions.flowLayout}"

        //dialogs
        const val materialDialog = "com.afollestad.material-dialogs:core:${Versions.materialDialog}"
        const val materialDialogLifecycle =
            "com.afollestad.material-dialogs:lifecycle:${Versions.materialDialog}"
        const val licensesDialog =
            "de.psdev.licensesdialog:licensesdialog:${Versions.licensesDialog}"
    }
}