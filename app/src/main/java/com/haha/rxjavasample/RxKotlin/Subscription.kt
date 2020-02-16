package com.haha.rxjavasample.RxKotlin

/**
 * 用于解绑定
 * todo 还未实现解绑逻辑
 */
interface Subscription {

    fun unSubscribe()

    fun isSubscribed():Boolean
}