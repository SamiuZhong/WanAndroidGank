package com.samiu.host.ui.activity

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import com.samiu.base.ui.BaseActivity
import com.samiu.host.R
import com.samiu.host.global.URL
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_browser.*

/**
 * @author Samiu 2020/3/4
 */
class BrowserActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_browser

    override fun initView() {
        tool_bar.title = getString(R.string.is_loading)
        tool_bar.navigationIcon = getDrawable(R.drawable.arrow_back)
        initWebView()
    }

    override fun initData() {
        tool_bar.setNavigationOnClickListener { onBackPressed() }
        intent.getStringExtra(URL)?.let {
            web_view.loadUrl(it)
        }
    }

    private fun initWebView() {
        progress_bar.progressDrawable = getDrawable(R.drawable.color_progressbar)
        web_view.run {
            webViewClient = object : WebViewClient() {

                override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                    super.onPageStarted(p0, p1, p2)
                    progress_bar.visibility = View.VISIBLE
                }

                override fun onPageFinished(p0: WebView?, p1: String?) {
                    super.onPageFinished(p0, p1)
                    progress_bar.visibility = View.GONE
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(p0: WebView?, p1: Int) {
                    super.onProgressChanged(p0, p1)
                    progress_bar.progress = p1
                    Log.e("browser", p1.toString())
                }

                override fun onReceivedTitle(p0: WebView?, p1: String?) {
                    super.onReceivedTitle(p0, p1)
                    p1?.let { tool_bar.title = p1 }
                }

            }
        }
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) web_view.goBack()
        super.onBackPressed()
    }
}