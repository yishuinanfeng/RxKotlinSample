package com.haha.rxjavasample.RxKotlin

/**
 * 线程调度
 */
abstract class Scheduler {

    abstract fun createWorker(): Worker

    abstract class Worker : Subscription {

        abstract fun schedule(action: Action0): Subscription
    }
}