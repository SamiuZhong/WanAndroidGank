package com.samiu.host.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * @author Samiu 2020/5/21
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jump()
    }

    private fun jump() {
        runBlocking {
            delay(2000)
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
