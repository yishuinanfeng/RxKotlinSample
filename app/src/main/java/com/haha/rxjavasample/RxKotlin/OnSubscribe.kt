package com.haha.rxjavasample.RxKotlin

interface OnSubscribe<T> :Action1<Subscriber<in T>>{
    //call从下往上拼装Subscriber的过程
}