package com.samiu.host.ui.fragment

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.samiu.base.ui.BaseFragment
import com.samiu.host.R
import com.samiu.host.databinding.FragmentMineBinding
import com.samiu.host.global.GITHUB_PAGE
import com.samiu.host.global.MY_BLOG
import com.samiu.host.global.openNativeBrowser
import de.psdev.licensesdialog.LicensesDialog
import de.psdev.licensesdialog.licenses.MITLicense
import de.psdev.licensesdialog.model.Notice
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * @author Samiu 2020/3/2
 */
class MineFragment : BaseFragment<FragmentMineBinding>() {
    override fun getLayoutResId() = R.layout.fragment_mine
    override fun initData() = Unit


    override fun initView() {
        text_1.setOnClickListener {
            val notice = Notice(getString(R.string.app_name), GITHUB_PAGE, "", MITLicense())
            LicensesDialog
                .Builder(activity)
                .setNotices(notice)
                .build()
                .show()
        }
        text_2.setOnClickListener { activity?.openNativeBrowser(GITHUB_PAGE) }
        text_3.setOnClickListener {
            LicensesDialog
                .Builder(activity)
                .setNotices(R.raw.licenses)
                .build()
                .show()
        }
        text_4.setOnClickListener { activity?.openNativeBrowser(MY_BLOG) }
        text_5.setOnClickListener {
            MaterialDialog(requireContext()).show {
                customView(R.layout.layout_image_dialog)
            }
        }
    }
}