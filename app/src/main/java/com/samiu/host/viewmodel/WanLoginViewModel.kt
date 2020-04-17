package com.samiu.host.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.samiu.host.model.repository.WanLoginRepository

/**
 * @author Samiu 2020/4/17
 */
class WanLoginViewModel(
    private val loginRepository: WanLoginRepository
) : ViewModel() {

    val userName = ObservableField("")
    val password = ObservableField("")

}