package com.samiu.host.global

import android.content.Intent
import androidx.fragment.app.Fragment
import com.samiu.host.ui.activity.BrowserActivity

/**
 * @author Samiu 2020/3/4
 */
const val URL = "url"

fun toBrowser(fragment: Fragment, url: String) =
    fragment.startActivity(Intent(fragment.context, BrowserActivity::class.java).apply {
        putExtra(URL, url)
    })