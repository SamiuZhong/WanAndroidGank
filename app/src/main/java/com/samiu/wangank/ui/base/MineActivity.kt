package com.samiu.wangank.ui.base

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityMineBinding
import com.samiu.wangank.global.GITHUB_PAGE
import com.samiu.wangank.global.MY_BLOG
import com.samiu.wangank.util.openNativeBrowser
import de.psdev.licensesdialog.LicensesDialog
import de.psdev.licensesdialog.licenses.MITLicense
import de.psdev.licensesdialog.model.Notice

/**
 * @author Samiu 2020/5/21
 * @email samiuzhong@outlook.com
 */
class MineActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMineBinding::inflate)
    override fun getBindingRoot() = binding.root
    override fun initData() = Unit

    override fun initView() {
        binding.run {
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