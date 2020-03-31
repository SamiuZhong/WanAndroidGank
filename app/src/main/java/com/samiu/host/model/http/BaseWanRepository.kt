package com.samiu.host.model.http

import com.samiu.host.model.bean.WanResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * @author Samiu 2020/3/3
 */
open class BaseWanRepository {

    suspend fun <T : Any> readyCall(
        call: suspend () -> WanResult<T>,
        errorMessage: String
    ): WanResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            WanResult.Error(
                IOException(
                    errorMessage,
                    e
                )
            )
        }
    }

    suspend fun <T : Any> call(
        response: WanResponse<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): WanResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                WanResult.Error(
                    IOException(
                        response.errorMsg
                    )
                )
            } else {
                successBlock?.let { it() }
                WanResult.Success(response.data)
            }
        }
    }
}