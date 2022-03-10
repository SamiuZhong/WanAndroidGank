package com.samiu.wangank.ui.mine.login

import com.samiu.wangank.bean.base.WanResult
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient

/**
 * @author Samiu 2020/4/17
 * @email samiuzhong@outlook.com
 */
class WanMineRepository : BaseWanRepository() {

    suspend fun login(userName: String, passWord: String) = readyCall {
        call(WanClient.service.login(userName, passWord))
    }

    suspend fun logout() = readyCall {
        call(WanClient.service.logout())
    }

    suspend fun share(title: String, link: String): WanResult<String> {
        var result: WanResult<String>?
        result = readyCall {
            call(WanClient.service.shareArticle(title, link))
        }
        return result
    }
}