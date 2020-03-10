package com.samiu.host.global

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.samiu.host.ui.activity.BrowserActivity
import com.samiu.host.ui.activity.RecyclerActivity

/**
 * @author Samiu 2020/3/4
 */
fun toBrowser(fragment: Fragment, url: String) =
    fragment.startActivity(Intent(fragment.context, BrowserActivity::class.java).apply {
        putExtra(URL, url)
    })

fun toBrowser(activity: Activity, url: String) =
    activity.startActivity(Intent(activity, BrowserActivity::class.java).apply {
        putExtra(URL, url)
    })

fun toRecycler(fragment: Fragment, cid: Int, title: String) =
    fragment.startActivity(Intent(fragment.context, RecyclerActivity::class.java).apply {
        putExtra(CID, cid)
        putExtra(TITLE, title)
    })

fun Context.openNativeBrowser(url:String){
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).run { startActivity(this) }
}