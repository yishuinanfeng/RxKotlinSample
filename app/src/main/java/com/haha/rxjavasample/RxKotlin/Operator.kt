package com.haha.rxjavasample.RxKotlin

interface Operator<T, R> : Func1<Subscriber<in T>, Subscriber<in R>>