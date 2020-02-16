package com.haha.rxjavasample.RxKotlin

class MainThreadScheduler : Scheduler() {

    override fun createWorker(): Worker {
        return MainWorker()
    }
}