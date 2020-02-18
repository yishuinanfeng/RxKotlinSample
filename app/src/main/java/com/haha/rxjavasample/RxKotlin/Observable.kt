package com.haha.rxjavasample.RxKotlin

class Observable<T>(val onSubscribe: OnSubscribe<T>) {

    companion object {
        fun <T> create(onSubscribe: OnSubscribe<T>): Observable<T> {
            return Observable(onSubscribe)
        }
    }

    /**
     * 调用onSubscribe.call，包装Subscriber
     */
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

    fun observeOn(scheduler: Scheduler): Observable<T> {
        return lift(OperatorObserveOn(scheduler))
    }

    /**
     * @param operator:从T转换到R
     * @return 一个持有R的Observable
     */
    fun <R> lift(operator: Operator<R, T>): Observable<R> {
        return create(OnSubscribeLift(onSubscribe, operator))
    }
}