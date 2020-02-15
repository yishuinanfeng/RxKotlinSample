package com.haha.rxjavasample.RxKotlin

abstract class Subscriber<T> : Observer<T>, Subscription {

    abstract fun onStart()

    override fun unSubscribe() {

    }

     override fun isSubscribed(): Boolean {
        return true
    }
}