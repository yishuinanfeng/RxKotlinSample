package com.haha.rxjavasample.RxKotlin

interface Func1<in T, out R> {
    fun call(t: T): R
}

