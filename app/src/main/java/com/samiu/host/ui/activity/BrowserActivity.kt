package com.samiu.host.ui.activity

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import com.samiu.base.ui.BaseActivity
import com.samiu.host.R
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_browser.*

/**
 * @author Samiu 2020/3/4
 */
class BrowserActivity : BaseActivity() {

    companion object {
        const val URL = "url"
    }

    override fun getLayoutResId() = R.layout.activity_browser

    override fun initView() {
        toolBar.title = getString(R.string.is_loading)
        toolBar.navigationIcon = getDrawable(R.drawable.arrow_back)
        initWebView()
    }

    override fun initData() {
        toolBar.setNavigationOnClickListener { onBackPressed() }
        intent.getStringExtra(URL)?.let {
            webView.loadUrl(it)
        }
    }

    private fun initWebView() {
        progressBar.progressDrawable = getDrawable(R.drawable.color_progressbar)
        webView.run {
            webViewClient = object : WebViewClient() {

                override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                    super.onPageStarted(p0, p1, p2)
                    progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(p0: WebView?, p1: String?) {
                    super.onPageFinished(p0, p1)
                    progressBar.visibility = View.GONE
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(p0: WebView?, p1: Int) {
                    super.onProgressChanged(p0, p1)
                    progressBar.progress = p1
                    Log.e("browser", p1.toString())
                }

                override fun onReceivedTitle(p0: WebView?, p1: String?) {
                    super.onReceivedTitle(p0, p1)
                    p1?.let { toolBar.title = p1 }
                }

            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack()
        super.onBackPressed()
    }
}