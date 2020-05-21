package com.samiu.host.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.samiu.base.ui.dataBinding
import com.samiu.host.R
import com.samiu.host.databinding.ActivityMainBinding
import com.samiu.host.global.desTo
import com.samiu.host.ui.base.nav.*

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : AppCompatActivity(),
    Toolbar.OnMenuItemClickListener,
    NavController.OnDestinationChangedListener {

    private val binding: ActivityMainBinding by dataBinding(R.layout.activity_main)
    private val bottomNavDrawer: BottomNavDrawerFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavDrawerFragment
    }

    fun getNavController(): NavController {
        return findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBottomNavigationAndFab()
    }

    private fun setUpBottomNavigationAndFab() {
        binding.run {
            getNavController().addOnDestinationChangedListener(
                this@MainActivity
            )
        }

        //给fab整个动画
        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            //点击事件
            setOnClickListener {
                val des = getNavController().currentDestination
                when (des?.id) {
                    R.id.browserFragment -> getNavController().popBackStack()
                }
            }
        }

        bottomNavDrawer.apply {
            //给底部栏的尖尖图标加上一个旋转跳跃的action
            addOnSlideAction(HalfClockwiseRotateSlideAction(binding.bottomAppBarChevron))
            //给底部栏的文字加上一个透明度的action
            addOnSlideAction(AlphaSlideAction(binding.bottomAppBarTitle, true))
            //给fab安排上显示和隐藏的action
            addOnStateChangedAction(ShowHideFabStateAction(binding.fab))
            //底部菜单的替换
            addOnStateChangedAction(ChangeSettingsMenuStateAction { showSettings ->
                binding.bottomAppBar.replaceMenu(
                    if (showSettings)
                        R.menu.bottom_app_bar_settings_menu
                    else
                        getBottomAppBarMenuForDestination()
                )
            })
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
            R.id.browserFragment -> {
                setBottomAppBarForEmail(getBottomAppBarMenuForDestination(destination))
                binding.bottomAppBar.visibility = View.INVISIBLE
            }
            else -> {
                setBottomAppBarForHome(getBottomAppBarMenuForDestination())
                binding.bottomAppBar.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 不同的页面实现不同的底部栏Menu
     */
    @MenuRes
    private fun getBottomAppBarMenuForDestination(destination: NavDestination? = null): Int {
        val dest = destination ?: findNavController(R.id.nav_host_fragment).currentDestination
        return when (dest?.id) {
            R.id.browserFragment -> {
                R.menu.bottom_app_bar_email_menu
            }
            else -> {
                R.menu.bottom_app_bar_home_menu
            }
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
            R.id.menu_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.menu_settings -> {
                //TODO click setting button to dark theme
                bottomNavDrawer.close()
            }
        }
        return true
    }
}