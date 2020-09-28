package com.samiu.wangank.model.repository

import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient

/**
 * @author Samiu 2020/4/17
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanLoginRepository : BaseWanRepository() {

    suspend fun login(userName: String, passWord: String) = readyCall{
        call(WanClient.service.login(userName, passWord))
    }

    suspend fun logout() = readyCall{
        call(WanClient.service.logout())
    }
}