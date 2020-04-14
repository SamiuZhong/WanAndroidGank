package com.samiu.host.global

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.samiu.host.R
import com.samiu.host.ui.base.BrowserActivity
import com.samiu.host.ui.base.MainActivity
import com.samiu.host.ui.base.RecyclerActivity

/**
 * @author Samiu 2020/3/4
 */
fun toBrowser(fragment: Fragment, url: String) {
    val bundle = Bundle().apply { putString(URL, url) }
    (fragment.requireActivity() as MainActivity).getNavController()
        .navigate(R.id.to_browser, bundle)
}

fun toBrowser(activity: Activity, url: String) {
    val bundle = Bundle().apply { putString(URL, url) }
    (activity as MainActivity).getNavController()
        .navigate(R.id.to_browser, bundle)
}

fun toRecycler(fragment: Fragment, cid: Int, title: String) =
    fragment.startActivity(Intent(fragment.context, RecyclerActivity::class.java).apply {
        putExtra(CID, cid)
        putExtra(TITLE, title)
    })

fun Context.openNativeBrowser(url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).run { startActivity(this) }
}