package com.samiu.host.ui.base.nav

import android.accounts.Account
import androidx.recyclerview.widget.ListAdapter

/**
 * @author Samiu 2020/3/31
 */
class AccountAdapter(
    private val listener:AccountAdapterListener
) {

    interface AccountAdapterListener{
        fun onAccountClicked(account:Account)
    }
}