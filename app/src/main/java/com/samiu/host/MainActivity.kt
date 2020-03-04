package com.samiu.host

import android.view.MotionEvent
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.samiu.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_main
    override fun initData() = Unit

    override fun initView() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNav.setupWithNavController(navController)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var y1 = 0f
        var y2 = 0f
        if (event?.action == MotionEvent.ACTION_DOWN) {
            y1 = event.y
        }
        if (event?.action == MotionEvent.ACTION_UP) {
            y2 = event.y
            if (y2 - y1 > 40)
                showBottomNav(true)
            else if (y1 - y2 > 40)
                showBottomNav(false)
        }
        return super.onTouchEvent(event)
    }

    private fun showBottomNav(show: Boolean) {
        if (show && !bottomNav.isShown)
            bottomNav.visibility = View.GONE
        else if (!show && bottomNav.isShown)
            bottomNav.visibility = View.INVISIBLE
    }
}
