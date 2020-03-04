package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.MainActivity
import com.samiu.host.R
import com.samiu.host.ui.adapter.ImageBannerAdapter
import com.samiu.host.model.bean.wan.Banner
import com.samiu.host.ui.viewmodel.wan.HomeViewModel
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_wan_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 玩安卓 首页
 * @author Samiu 2020/3/2
 */
class WanHomeFragment : BaseVMFragment<HomeViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_home
    override fun initView() = Unit
    override fun initData() = Unit

    private val mViewModel: HomeViewModel by viewModel()

    override fun startObserve() {
        mViewModel.run {
            mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
        }
    }

    private fun setBanner(bannerList: List<Banner>) {
        banner.adapter = ImageBannerAdapter(bannerList)
        banner.setOnBannerListener(object : OnBannerListener<Banner> {
            override fun onBannerChanged(position: Int) = Unit
            override fun OnBannerClick(data: Banner?, position: Int) {
                data?.url?.let {
                    (activity as MainActivity).toBrowser(it)
                }
            }
        })
        banner.start()
    }

    override fun onStart() {
        super.onStart()
        banner.start()
    }

    override fun onStop() {
        super.onStop()
        banner.stop()
    }
}