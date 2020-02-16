package com.haha.rxjavasample.RxKotlin

import android.util.Log
import com.haha.rxjavasample.TAG
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

private const val PREFIX = "RxKotlin_thread"
/**
 * 默认线程工厂
 */
class RxThreadFactory : AtomicInteger(), ThreadFactory {

    override fun newThread(r: Runnable?): Thread {
        val thread = Thread(r)
        //守护线程
        thread.isDaemon = true
        thread.name = "${PREFIX}_${incrementAndGet()}"
        Log.d(TAG, "newThread $thread.name")
        return thread
    }

    //下面是三个方法不应该属于AtomicInteger中，但是编译器莫名其妙让我一定要实现！！！
    override fun toByte(): Byte {
        return Byte.MIN_VALUE
    }

    override fun toChar(): Char {
        return Char.MIN_VALUE
    }

    override fun toShort(): Short {
        return Short.MIN_VALUE
    }

}