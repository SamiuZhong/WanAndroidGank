package com.samiu.host.ui.wan.nav.fragment

import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.adapter.ImageBannerAdapter
import com.samiu.host.model.bean.wan.Banner
import com.samiu.host.ui.wan.nav.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_wan_home.*

/**
 * 玩安卓 首页
 * @author Samiu 2020/3/2
 */
class WanHomeFragment : BaseVMFragment<HomeViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_home
    override fun initData() = Unit
    override fun initView(){
        val bannerList = ArrayList<Banner>()
        bannerList.add(Banner(
            "", 29,
            "https://wanandroid.com/blogimgs/cd53b340-1d94-4810-b864-567574e85de7.jpeg",
            1,0,"asdaasd",0,""
        ))
        bannerList.add(Banner(
            "", 29,
            "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
            1,1,"asdaasd",1,""
        ))
        banner.adapter = ImageBannerAdapter(bannerList)
        banner.start()
    }

//    private val mViewModel: HomeViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        banner.start()
    }

    override fun onStop() {
        super.onStop()
        banner.stop()
    }

    override fun startObserve() {
//        mViewModel.apply {
//            mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
//        }
    }

    private fun setBanner(bannerList:List<Banner>){
        banner.adapter = ImageBannerAdapter(bannerList)
        banner.start()
    }
}