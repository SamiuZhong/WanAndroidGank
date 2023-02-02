package com.samiu.wangank.base

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
class AppApplication : Application() {

    companion object {
        var context: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin {
            androidContext(this@AppApplication)
            modules(module)
        }
    }
}