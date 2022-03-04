package com.samiu.wangank.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.samiu.wangank.global.TITLE
import com.samiu.wangank.global.URL
import com.samiu.wangank.ui.base.BrowserActivity
import com.samiu.wangank.ui.base.MainActivity

/**
 * @author Samiu 2020/3/4
 * @email samiuzhong@outlook.com
 */
fun desTo(fragment: Fragment, id: Int) {
    val navController = (fragment.requireActivity() as MainActivity).getNavController()
    try {
        val desId = navController.getBackStackEntry(id).destination.id
        navController.popBackStack(desId, false)
    } catch (e: IllegalArgumentException) {
        navController.navigate(id)
    }
}

fun Context.openNativeBrowser(url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).run { startActivity(this) }
}

fun Context.toBrowser(url: String,title: String) {
    val intent = Intent(this, BrowserActivity::class.java).apply {
        putExtra(URL, url)
        putExtra(TITLE,title)
    }
    startActivity(intent)
}