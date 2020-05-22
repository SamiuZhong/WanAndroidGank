package com.samiu.host.global

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.samiu.host.R
import com.samiu.host.ui.base.MainActivity
import com.samiu.host.ui.base.SystemDisplayActivity

/**
 * @author Samiu 2020/3/4
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

fun toBrowserFragment(fragment: Fragment, url: String) {
    val bundle = Bundle().apply { putString(URL, url) }
    (fragment.requireActivity() as MainActivity).getNavController()
        .navigate(R.id.browserFragment, bundle)
}

fun toBrowserFragment(activity: Activity, url: String) {
    val bundle = Bundle().apply { putString(URL, url) }
    (activity as MainActivity).getNavController()
        .navigate(R.id.browserFragment, bundle)
}

fun Context.openNativeBrowser(url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).run { startActivity(this) }
}

fun toSystemList(fragment: Fragment, cid: Int, title: String) =
    fragment.startActivity(Intent(fragment.context, SystemDisplayActivity::class.java).apply {
        putExtra(CID, cid)
        putExtra(TITLE, title)
    })

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