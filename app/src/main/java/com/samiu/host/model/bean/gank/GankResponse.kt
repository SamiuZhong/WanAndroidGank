package com.samiu.host.model.bean.gank

/**
 * @author Samiu 2020/3/9
 */
data class GankResponse<out T>(val error:Boolean,val results:T) {
}