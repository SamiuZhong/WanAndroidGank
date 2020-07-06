package com.samiu.host.global

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import androidx.fragment.app.Fragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.samiu.host.ui.base.BrowserActivity
import com.samiu.host.ui.base.MainActivity
import com.samiu.host.ui.base.SystemDisplayActivity

/**
 * @author Samiu 2020/3/4
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
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

fun drawShape(
    context: Context, cornerSize: Float, color: Int
) = MaterialShapeDrawable(
    context,
    null,
    0,
    0
).apply {
    fillColor = ColorStateList.valueOf(color)
    shapeAppearanceModel = shapeAppearanceModel
        .toBuilder()
        .setAllCornerSizes(cornerSize)
        .build()
}

fun drawShape(
    context: Context,
    ltCorner: Float,
    rtCorner: Float,
    lbCorner: Float,
    rbCorner: Float,
    color: Int
) = MaterialShapeDrawable(
    context,
    null,
    0,
    0
).apply {
    fillColor = ColorStateList.valueOf(color)
    shapeAppearanceModel = shapeAppearanceModel
        .toBuilder()
        .setTopLeftCornerSize(ltCorner)
        .setTopRightCornerSize(rtCorner)
        .setBottomLeftCornerSize(lbCorner)
        .setBottomRightCornerSize(rbCorner)
        .build()
}

fun drawShape(
    context: Context, cornerSize: Float, color: Int, lineColor: Int
) = MaterialShapeDrawable(
    context,
    null,
    0,
    0
).apply {
    fillColor = ColorStateList.valueOf(color)
    strokeWidth = 1F
    strokeColor = ColorStateList.valueOf(lineColor)
    shapeAppearanceModel = shapeAppearanceModel
        .toBuilder()
        .setAllCornerSizes(cornerSize)
        .build()
}