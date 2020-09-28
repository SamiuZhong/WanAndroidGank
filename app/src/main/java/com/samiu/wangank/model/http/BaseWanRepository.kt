package com.samiu.wangank.model.http

import com.samiu.wangank.model.bean.WanResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
open class BaseWanRepository {

    suspend fun <T : Any> readyCall(
        call: suspend () -> WanResult<T>,
    ): WanResult<T> = try {
        call()
    } catch (e: Exception) {
        WanResult.Error(e.toString())
    }

    suspend fun <T : Any> call(
        response: WanResponse<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): WanResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                WanResult.Error(response.errorMsg)
            } else {
                successBlock?.let { it() }
                WanResult.Success(response.data)
            }
        }
    }
}