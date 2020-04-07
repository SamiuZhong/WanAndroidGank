package com.samiu.host.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.ActivityMainBinding
import com.samiu.host.ui.base.nav.*
import kotlin.LazyThreadSafetyMode.NONE

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : BaseActivity(),
    Toolbar.OnMenuItemClickListener,
    NavController.OnDestinationChangedListener {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun getBindingRoot() = binding.root
    override fun initData() = Unit
    override fun initView() = setUpBottomNavigationAndFab()

    private val bottomNavDrawer: BottomNavDrawerFragment by lazy(NONE) {
        supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavDrawerFragment
    }

    private fun setUpBottomNavigationAndFab() {
        binding.run {
            findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(
                this@MainActivity
            )
        }

        //给fab整个动画
        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            //todo fab.setOnclickListener
        }

        bottomNavDrawer.apply {
            //给底部栏的尖尖图标加上一个旋转跳跃的action
            addOnSlideAction(HalfClockwiseRotateSlideAction(binding.bottomAppBarChevron))
            //给底部栏的文字加上一个透明度的action
            addOnSlideAction(AlphaSlideAction(binding.bottomAppBarTitle, true))
            //给fab安排上显示和隐藏的action
            addOnStateChangedAction(ShowHideFabStateAction(binding.fab))
            //底部菜单的弹出和收回
            addOnStateChangedAction(ChangeSettingsMenuStateAction { showSettings ->
                binding.bottomAppBar.replaceMenu(
                    if (showSettings)
                        R.menu.bottom_app_bar_settings_menu
                    else
                        getBottomAppBarMenuForDestination()
                )
            })
            //sandwich滑动的时候底部栏的那个尖尖图标也要旋转跳跃
            addOnSandwichSlideAction(HalfCounterClockwiseRotateSlideAction(binding.bottomAppBarChevron))
        }

        //设置底部栏Menu的点击事件
        binding.bottomAppBar.apply {
            setNavigationOnClickListener {
                bottomNavDrawer.toggle()
            }
            setOnMenuItemClickListener(this@MainActivity)
        }
        //设置底部栏我们自定义那一坨（左下角）的点击事件
        binding.bottomAppBarContentContainer.setOnClickListener {
            bottomNavDrawer.toggle()
        }
    }

    /**
     * 当页面发生改变时，我们让fab的长相也跟着做相应的变化
     */
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.browserFragment ->
                setBottomAppBarForEmail(getBottomAppBarMenuForDestination(destination))
            else ->
                setBottomAppBarForHome(getBottomAppBarMenuForDestination())
        }
    }

    /**
     * 不同的页面实现不同的底部栏Menu
     */
    @MenuRes
    private fun getBottomAppBarMenuForDestination(destination: NavDestination? = null): Int {
        val dest = destination ?: findNavController(R.id.nav_host_fragment).currentDestination
        return when (dest?.id) {
            R.id.browserFragment -> R.menu.bottom_app_bar_email_menu
            else -> R.menu.bottom_app_bar_home_menu
        }
    }

    private fun setBottomAppBarForHome(@MenuRes menuRes: Int) {
        binding.run {
            fab.setImageState(intArrayOf(-android.R.attr.state_activated), true)
            bottomAppBar.visibility = View.VISIBLE
            bottomAppBar.replaceMenu(menuRes)
            fab.contentDescription = getString(R.string.fab_compose_email_content_description)
            bottomAppBarTitle.visibility = View.VISIBLE
            bottomAppBar.performShow()
            fab.show()
        }
    }

    private fun setBottomAppBarForEmail(@MenuRes menuRes: Int) {
        binding.run {
            fab.setImageState(intArrayOf(android.R.attr.state_activated), true)
            bottomAppBar.visibility = View.VISIBLE
            bottomAppBar.replaceMenu(menuRes)
            fab.contentDescription = getString(R.string.fab_reply_email_content_description)
            bottomAppBarTitle.visibility = View.INVISIBLE
            bottomAppBar.performShow()
            fab.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_settings -> {
                bottomNavDrawer.close()
//                showDarkThemeMenu()
            }
        }
        return true
    }

//    private fun showDarkThemeMenu() {
//        MenuBottomSheetDialogFragment(R.menu.dark_theme_bottom_sheet_menu) {
//            onDarkThemeMenuItemSelected(it.itemId)
//        }.show(supportFragmentManager, null)
//    }

//    /**
//     * 夜间模式
//     */
//    private fun onDarkThemeMenuItemSelected(itemId: Int): Boolean {
//        val nightMode = when (itemId) {
//            R.id.menu_light -> AppCompatDelegate.MODE_NIGHT_NO
//            R.id.menu_dark -> AppCompatDelegate.MODE_NIGHT_YES
//            R.id.menu_battery_saver -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
//            R.id.menu_system_default -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
//            else -> return false
//        }
//
//        delegate.localNightMode = nightMode
//        return true
//    }
}
