package com.samiu.wangank.ui.base

import android.view.ViewGroup
import android.webkit.WebView
import com.just.agentweb.AgentWeb
import com.just.agentweb.AgentWebSettingsImpl
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebChromeClient
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityBrowserBinding
import com.samiu.wangank.global.TITLE
import com.samiu.wangank.global.URL

/**
 * @author Samiu 2020/3/4
 * @email samiuzhong@outlook.com
 */
class BrowserActivity : BaseActivity() {
    private val binding by viewBinding(ActivityBrowserBinding::inflate)
    override fun getBindingRoot() = binding.root

    private var mAgentWeb: AgentWeb? = null
    private lateinit var mUrl: String
    private lateinit var mTitle: String

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        intent.getStringExtra(URL)?.let {
            mUrl = it
        }
        intent.getStringExtra(TITLE)?.let {
            mTitle = it
        }
    }

    override fun initData() = initWebView()

    private fun initWebView() {
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(
                binding.browserRoot,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            .useDefaultIndicator(getColor(R.color.color_80DED9), 3)
            .interceptUnkownUrl()
            .setAgentWebWebSettings(AgentWebSettingsImpl.getInstance())
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
            .setWebChromeClient(object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    binding.toolbarTitle.text = title
                    super.onReceivedTitle(view, title)
                }
            })
            .createAgentWeb()
            .ready()
            .get()
        mAgentWeb?.webCreator?.webView?.run {
            overScrollMode = WebView.OVER_SCROLL_NEVER
            settings.run {
                javaScriptCanOpenWindowsAutomatically = false
                loadsImagesAutomatically = true
                useWideViewPort = true
                loadWithOverviewMode = true
            }
        }
        mAgentWeb?.urlLoader?.loadUrl(mUrl)
    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }
}