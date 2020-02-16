package com.haha.rxjavasample.RxKotlin

/**
 * 创建具体的调度器工厂
 */
class Schedulers {

    companion object {
        fun io(): Scheduler {
            return NewThreadScheduler(RxThreadFactory())
        }

        fun main(){

        }
    }
}