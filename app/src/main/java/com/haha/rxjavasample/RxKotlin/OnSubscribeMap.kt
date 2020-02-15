package com.haha.rxjavasample.RxKotlin

import java.lang.Exception

class OnSubscribeMap<T, R>(val source: Observable<T>, val transformer: Func1<in T, out R>) :
    OnSubscribe<R> {

    override fun call(s: Subscriber<in R>) {
        val parent = MapSubscriber(s, transformer)
        source.subscribe(parent)
    }

    class MapSubscriber<T, R>(val actual: Subscriber<in R>, val mapper: Func1<in T, out R>) :
        Subscriber<T>() {

        override fun onStart() {
            actual.onStart()
        }

        override fun onNext(t: T) {
            val result: R
            try {
                //从T转为R
                result = mapper.call(t)
            } catch (ex: Exception) {
                unSubscribe()
                onError(ex)
                return
            }

            actual.onNext(result)
        }

        override fun onComplete() {
            actual.onComplete()
        }

        override fun onError(t: Throwable) {
            actual.onError(t)
        }

    }
}