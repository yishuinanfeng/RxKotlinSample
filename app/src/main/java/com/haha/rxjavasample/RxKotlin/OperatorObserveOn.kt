package com.haha.rxjavasample.RxKotlin

import java.lang.RuntimeException
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue

/**
 * Observe切换线程
 * 注意：没有背压处理
 */
class OperatorObserveOn<T>(val scheduler: Scheduler) : Operator<T, T> {

    override fun call(child: Subscriber<in T>): Subscriber<in T> {
        return ObserveOnSubscriber(scheduler, child)
    }

    class ObserveOnSubscriber<T>(scheduler: Scheduler, val child: Subscriber<in T>) :
        Subscriber<T>(), Action0 {
        //有界阻塞队列，容量128是RxJava默认容量
        private val queue = ArrayBlockingQueue<T>(128)
        private val worker = scheduler.createWorker()

        override fun onNext(t: T) {
            if (!queue.offer(t)) {
                //上游的速度过快，导致队列已满
                onError(RuntimeException("MissingBackpressure"))
                return
            }

            worker.schedule(this)
        }

        override fun onComplete() {
            child.onComplete()
        }

        override fun onError(t: Throwable) {
            child.onError(t)
        }

        //在指定的线程中执行,在从上往下执行onNext的过程中切换到另一个线程
        override fun call() {
            var isContinue = true
            while (isContinue) {
                val t = queue.poll()
                if (t == null) {
                    isContinue = false
                }

                child.onNext(t)
            }
        }

    }
}