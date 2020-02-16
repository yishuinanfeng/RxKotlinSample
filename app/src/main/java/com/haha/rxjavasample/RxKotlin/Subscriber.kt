package com.haha.rxjavasample.RxKotlin

abstract class Subscriber<T> : Observer<T>, Subscription {

    open fun onStart(){

    }

    override fun unSubscribe() {

    }

     override fun isSubscribed(): Boolean {
        return true
    }
}