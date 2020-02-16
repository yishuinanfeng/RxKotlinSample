package com.haha.rxjavasample.RxKotlin

import android.os.Handler
import android.os.Looper

class MainWorker : Scheduler.Worker(), Subscription {
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun schedule(action: Action0): Subscription {
        val scheduledAction = ScheduledAction(action)
        mainHandler.post(scheduledAction)
        return scheduledAction
    }

    override fun unSubscribe() {
    }

    override fun isSubscribed(): Boolean {
        //todo 暂时没处理
        return true
    }
}