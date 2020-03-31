package com.samiu.host.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.ActivityMainBinding

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : BaseActivity()
//    , Toolbar.OnMenuItemClickListener,
//    NavController.OnDestinationChangedListener
{

    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun getBindingRoot() = binding.root
    override fun initData() = Unit
    override fun initView() = setUpBottomNavigationAndFab()

    private fun setUpBottomNavigationAndFab() {
//        binding.run {
//            findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(
//                this@MainActivity
//            )
//        }

        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            //todo fab.setOnclickListener
        }

        binding.bottomAppBar.apply {
            setNavigationOnClickListener {

            }
        }
    }

//    override fun onMenuItemClick(item: MenuItem?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onDestinationChanged(
//        controller: NavController,
//        destination: NavDestination,
//        arguments: Bundle?
//    ) {
//        TODO("Not yet implemented")
//    }
}
