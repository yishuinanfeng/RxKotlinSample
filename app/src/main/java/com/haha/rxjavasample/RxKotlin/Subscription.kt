package com.haha.rxjavasample.RxKotlin

interface Subscription {

    fun unSubscribe()

    fun isSubscribed():Boolean
}