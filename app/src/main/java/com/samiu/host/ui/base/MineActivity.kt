package com.samiu.host.ui.base

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.ActivityMineBinding
import com.samiu.host.global.GITHUB_PAGE
import com.samiu.host.global.MY_BLOG
import com.samiu.host.global.openNativeBrowser
import de.psdev.licensesdialog.LicensesDialog
import de.psdev.licensesdialog.licenses.MITLicense
import de.psdev.licensesdialog.model.Notice

/**
 * @author Samiu 2020/5/21
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class MineActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityMineBinding::inflate)
    override fun getBindingRoot() = mBinding.root
    override fun initData() = Unit

    override fun initView() {
        mBinding.run {
            backIcon.setOnClickListener { finish() }
            text1.setOnClickListener {
                val notice = Notice(getString(R.string.app_name), GITHUB_PAGE, "", MITLicense())
                LicensesDialog
                    .Builder(this@MineActivity)
                    .setNotices(notice)
                    .build()
                    .show()
            }
            text2.setOnClickListener { this@MineActivity.openNativeBrowser(GITHUB_PAGE) }
            text3.setOnClickListener {
                LicensesDialog
                    .Builder(this@MineActivity)
                    .setNotices(R.raw.licenses)
                    .build()
                    .show()
            }
            text4.setOnClickListener { this@MineActivity.openNativeBrowser(MY_BLOG) }
            text5.setOnClickListener {
                MaterialDialog(this@MineActivity).show {
                    customView(R.layout.layout_image_dialog)
                }
            }
        }
    }
}