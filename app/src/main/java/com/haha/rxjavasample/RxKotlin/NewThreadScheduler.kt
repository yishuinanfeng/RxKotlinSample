package com.haha.rxjavasample.RxKotlin

import java.util.concurrent.ThreadFactory
/**
 * 普通子线程调度器
 */
class NewThreadScheduler(private val threadFactory: ThreadFactory) : Scheduler() {

    override fun createWorker(): Worker {
        return NewThreadWorker(threadFactory)
    }
}