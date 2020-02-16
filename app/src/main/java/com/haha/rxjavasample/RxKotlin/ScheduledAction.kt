package com.haha.rxjavasample.RxKotlin

import android.util.Log
import com.haha.rxjavasample.TAG

/**
 * 线程执行的具体任务
 */
class ScheduledAction(private val action0: Action0) : Runnable, Subscription {

    override fun run() {
        try {
            Log.d(TAG, "run scheduledAction")
            action0.call()
        } catch (throwable: Throwable) {
            Log.e(TAG, "execute scheduledAction:$throwable")
            val t = Thread.currentThread()
            t.uncaughtExceptionHandler?.uncaughtException(t, throwable)
        }
    }

    override fun unSubscribe() {
    }

    override fun isSubscribed(): Boolean {
        return true
    }
}