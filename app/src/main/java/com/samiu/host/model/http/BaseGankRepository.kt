package com.samiu.host.model.http

import com.samiu.host.model.bean.gank.GankResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * @author Samiu 2020/3/9
 */
open class BaseGankRepository {

    suspend fun <T : Any> readyCall(
        call: suspend () -> GankResult<T>,
        errorMessage: String
    ): GankResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            GankResult.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> call(
        response: GankResponse<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): GankResult<T> {
        return coroutineScope {
            if (response.error) {
                errorBlock?.let { it() }
                GankResult.Error(IOException(response.error.toString()))
            } else {
                successBlock?.let { it() }
                GankResult.Success(response.results)
            }
        }
    }
}