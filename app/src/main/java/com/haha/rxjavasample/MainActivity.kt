package com.haha.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.haha.rxjavasample.RxKotlin.*

const val TAG = "RxKotlin"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.create(object : OnSubscribe<String> {
            override fun call(t: Subscriber<in String>) {
                t.onStart()
                t.onNext("1")
            }
        }).map(object : Func1<String, Int> {

            override fun call(t: String): Int {
                Log.d(TAG, Thread.currentThread().name)
                return t.toInt()
            }
        })
            .subscribeOn(Schedulers.io())
            .map(object : Func1<Int, String> {

                override fun call(t: Int): String {
                    Log.d(TAG, Thread.currentThread().name)
                    return (t + 2).toString()
                }

            }).subscribe(object : Subscriber<String>() {
                override fun onStart() {

                }

                override fun onNext(t: String) {
                    Log.d(TAG, Thread.currentThread().name)
                    Log.d(TAG, t)
                }

                override fun onComplete() {
                }

                override fun onError(t: Throwable) {
                    Log.d(TAG, t.message)
                }
            })
    }
}
