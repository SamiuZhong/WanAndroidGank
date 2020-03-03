package com.samiu.host

import android.app.Application
import android.content.Context
import com.samiu.host.module.wanModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/3
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
            modules(wanModule)
        }
    }
}