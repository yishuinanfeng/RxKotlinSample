package com.haha.rxjavasample.RxKotlin

class Observable<T>(val onSubscribe: OnSubscribe<T>) {

    companion object {
        fun <T> create(onSubscribe: OnSubscribe<T>): Observable<T> {
            return Observable(onSubscribe)
        }
    }

    fun subscribe(subscriber: Subscriber<T>): Subscriber<T> {
        onSubscribe.call(subscriber)
        return subscriber
    }

    fun <R> map(func: Func1<in T, out R>): Observable<R> {
        return create(OnSubscribeMap(this, func))
    }

    /**
     * subscribe过程切换线程（简单认为是在该方法调用之前的链放在对应的线程执行）
     */
    fun subscribeOn(scheduler: Scheduler): Observable<T> {
        return create(OperatorSubscribeOn(scheduler, this))
    }
}