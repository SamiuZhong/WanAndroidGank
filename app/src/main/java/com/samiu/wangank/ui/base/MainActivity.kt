package com.samiu.wangank.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.samiu.base.ui.dataBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityMainBinding
import com.samiu.wangank.ui.nav.*
import com.samiu.wangank.ui.search.SearchActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
class MainActivity : AppCompatActivity(),
    Toolbar.OnMenuItemClickListener {

    private var mBackPressed: Boolean = false
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

        //给fab整个动画
        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            //点击事件
            setOnClickListener {
//                val des = getNavController().currentDestination
//                when (des?.id) {
//                    R.id.browserFragment -> getNavController().popBackStack()
//                }
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
            binding.bottomAppBar.replaceMenu(R.menu.bottom_app_bar_home_menu)
            addOnStateChangedAction(ChangeSettingsMenuStateAction { showSettings ->
                binding.bottomAppBar.replaceMenu(
                    if (showSettings)
                        R.menu.bottom_app_bar_settings_menu
                    else
                        R.menu.bottom_app_bar_home_menu
                )
            })
            //底部菜单文字的切换
            setOnCutover { binding.bottomAppBarTitle.setText(it) }
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

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.menu_settings -> {
                startActivity(Intent(this, MineActivity::class.java))
                bottomNavDrawer.toggle()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (!mBackPressed) {
            Toast.makeText(this, getString(R.string.repress_to_back), Toast.LENGTH_SHORT).show()
            mBackPressed = true
            MainScope().launch {
                delay(2000L)
                mBackPressed = false
            }
        } else finish()
    }
}