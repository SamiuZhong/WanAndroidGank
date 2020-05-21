package com.samiu.host.model.repository

import com.samiu.host.global.NETWORK_ERROR
import com.samiu.host.model.http.BaseWanRepository
import com.samiu.host.model.http.WanClient

/**
 * @author Samiu 2020/4/17
 */
class WanLoginRepository : BaseWanRepository() {

    suspend fun login(userName: String, passWord: String) = readyCall(
        call = {
            call(WanClient.service.login(userName, passWord))
        }, errorMessage = NETWORK_ERROR
    )

    suspend fun logout() = readyCall(
        call = {
            call(WanClient.service.logout())
        }, errorMessage = NETWORK_ERROR
    )
}