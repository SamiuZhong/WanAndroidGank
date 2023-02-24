package com.samiu.wangank.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samiu.wangank.databinding.ActivityBrowserBinding
import com.samiu.wangank.utils.Constants
import com.samiu.wangank.utils.viewBinding

/**
 * @author samiu 2023/2/23
 * @email samiuzhong@outlook.com
 */
class BrowserActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityBrowserBinding::inflate)

    private var mUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val url = intent?.getStringExtra(Constants.Bundle.URL)
        if (!url.isNullOrBlank()) {
            mUrl = url
            binding.webView.loadUrl(url)
        }
    }

    companion object {
        fun toBrowser(context: Context, url: String) {
            val intent = Intent(context, BrowserActivity::class.java)
            intent.putExtra(Constants.Bundle.URL, url)
            context.startActivity(intent)
        }
    }
}