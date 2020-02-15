package com.haha.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.haha.rxjavasample.RxKotlin.Func1
import com.haha.rxjavasample.RxKotlin.Observable
import com.haha.rxjavasample.RxKotlin.OnSubscribe
import com.haha.rxjavasample.RxKotlin.Subscriber

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
                return t.toInt()
            }
        }).subscribe(object : Subscriber<Int>() {
            override fun onStart() {

            }

            override fun onNext(t: Int) {
                Log.d(TAG, t.toString())
            }

            override fun onComplete() {
            }

            override fun onError(t: Throwable) {
            }

        })
    }
}
