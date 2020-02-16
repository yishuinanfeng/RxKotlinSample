package com.haha.rxjavasample.RxKotlin

import android.util.Log
import com.haha.rxjavasample.TAG
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ThreadFactory

/**
 * 子线程执行器
 */
class NewThreadWorker(threadFactory: ThreadFactory) : Scheduler.Worker(), Subscription {

    private val scheduledExecutorService: ExecutorService =
        Executors.newCachedThreadPool(threadFactory)

    override fun schedule(action: Action0): Subscription {
        val scheduledAction = ScheduledAction(action)
        scheduledExecutorService.execute(scheduledAction)
        Log.d(TAG, "execute scheduledAction")
        return scheduledAction
    }

    override fun unSubscribe() {

    }

    override fun isSubscribed(): Boolean {
        //暂时没处理
        return true
    }
}