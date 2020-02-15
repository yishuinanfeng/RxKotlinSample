package com.haha.rxjavasample.RxKotlin

interface Observer<T> {

    fun onNext(t: T)

    fun onComplete()

    fun onError(t: Throwable)
}