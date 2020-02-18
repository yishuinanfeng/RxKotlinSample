package com.haha.rxjavasample.RxKotlin

import java.lang.Exception

/**
 * 线程调度器
 */
class OperatorSubscribeOn<T>(val scheduler: Scheduler, val source: Observable<T>) : OnSubscribe<T> {

    override fun call(s: Subscriber<in T>) {
        val worker = scheduler.createWorker()
        val subscribeOnSubscriber = SubscribeOnSubscriber(s, worker, source)
        //将source的subscribe放到子线程
        worker.schedule(subscribeOnSubscriber)
    }

    class SubscribeOnSubscriber<T>(
        val actual: Subscriber<in T>,
        val worker: Scheduler.Worker,
        var source: Observable<T>?
    ) :
        Subscriber<T>(), Action0 {

        override fun onStart() {
            actual.onStart()
        }

        override fun onNext(t: T) {
            actual.onNext(t)
        }

        override fun onComplete() {
            try {
                actual.onComplete()
            } catch (ex: Exception) {
                worker.unSubscribe()
            }
        }

        override fun onError(t: Throwable) {
            try {
                actual.onError(t)
            } catch (ex: Exception) {
                worker.unSubscribe()
            }
        }

        //这个方法会在Worker指定的线程执行，在从下往上包装Subscriber的过程中切换到另一个线程
        override fun call() {
            val src = source
            //这里RxJava源码也是置为空的，我的理解是执行完该方法后，就可以释放对Observable的持有了
            source = null
            src?.subscribe(this)
        }
    }
}