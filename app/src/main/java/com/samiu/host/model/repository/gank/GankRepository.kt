package com.samiu.host.model.repository.gank

import com.samiu.host.model.http.BaseGankRepository
import com.samiu.host.model.http.GankClient

/**
 * @author Samiu 2020/3/9
 */
class GankRepository : BaseGankRepository() {

    suspend fun getAndroidData(page: Int) = readyCall(
        call = {
            call(GankClient.service.getAndroid(page))
        }, errorMessage = false.toString()
    )

    suspend fun getIosData(page: Int) = readyCall(
        call = {
            call(GankClient.service.getIos(page))
        }, errorMessage = false.toString()
    )

    suspend fun getFrontData(page: Int) = readyCall(
        call = {
            call(GankClient.service.getFront(page))
        }, errorMessage = false.toString()
    )

    suspend fun getExpandData(page: Int) = readyCall(
        call = {
            call(GankClient.service.getExpand(page))
        }, errorMessage = false.toString()
    )
}