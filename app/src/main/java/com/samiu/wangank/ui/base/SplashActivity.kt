package com.samiu.wangank.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 启动页
 *
 * @author Samiu 2020/5/21
 * @email samiuzhong@outlook.com
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jump()
    }

    private fun jump() {
        runBlocking {
            delay(1000)
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
