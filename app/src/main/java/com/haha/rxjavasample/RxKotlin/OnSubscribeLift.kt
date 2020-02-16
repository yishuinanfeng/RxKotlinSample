package com.haha.rxjavasample.RxKotlin

import java.lang.Exception

class OnSubscribeLift<T, R>(private val parent: OnSubscribe<T>, private val operator: Operator<out R, in T>) :
    OnSubscribe<R> {

    override fun call(s: Subscriber<in R>) {
        val subscriber: Subscriber<in T> = operator.call(s)
        try {
            try {
                subscriber.onStart()
                //在往上拼装Subscriber，使用operator处理返回的Subscriber。之后从上往下执行onNext就会执行operator定义的逻辑
                parent.call(subscriber)
            } catch (t: Throwable) {
                subscriber.onError(t)
            }
        } catch (t: Throwable) {
            s.onError(t)
        }

    }
}