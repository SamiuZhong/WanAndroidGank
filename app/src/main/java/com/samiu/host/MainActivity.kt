package com.samiu.host

import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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

    private var y1 = 0f
    private var y2 = 0f

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            y1 = event.rawY
        }
        if (event?.action == MotionEvent.ACTION_UP) {
            y2 = event.rawY
            if (y2 - y1 > 100)
                showBottomNav(true)
            else if (y1 - y2 > 100)
                showBottomNav(false)
        }
        return super.dispatchTouchEvent(event)
    }

    private fun showBottomNav(show: Boolean) {
        if (show && !bottomNav.isShown) {        //show
            val inAnim = AnimationUtils.loadAnimation(this, R.anim.in_bottom)
            bottomNav.startAnimation(inAnim)
            inAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) = Unit
                override fun onAnimationEnd(animation: Animation?) = Unit
                override fun onAnimationStart(animation: Animation?) {
                    bottomNav.visibility = View.VISIBLE
                }
            })
        } else if (!show && bottomNav.isShown) {  //hide
            val outAnim = AnimationUtils.loadAnimation(this, R.anim.out_bottom)
            bottomNav.startAnimation(outAnim)
            bottomNav.visibility = View.GONE
        }
    }
}
