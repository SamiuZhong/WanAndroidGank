package com.samiu.wangank.data.repository

import android.util.Log
import com.samiu.wangank.model.WanResponse

/**
 * @author samiu 2023/2/24
 * @email samiuzhong@outlook.com
 */
open class BaseRepository {

    companion object {
        private const val TAG = "Request##"
    }

    suspend fun <T : Any> safeRequest(
        call: suspend () -> WanResponse<T>
    ): WanResponse<T> {
        Log.d(TAG, "safeRequest: start")
        val response = call()
        if (response.errorCode != 0) {
            Log.e(
                TAG, "safeRequest: error code=[${response.errorCode}], msg=[${response.errorMsg}]"
            )
        }
        return response
    }
}