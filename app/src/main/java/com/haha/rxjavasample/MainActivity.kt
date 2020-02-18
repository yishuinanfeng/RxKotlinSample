package com.haha.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.haha.rxjavasample.RxKotlin.*

const val TAG = "RxKotlin"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Observable.create(object : OnSubscribe<Int> {
            override fun call(t: Subscriber<in Int>) {
                t.onStart()
                t.onNext(1)
            }
        }).subscribe(object : Subscriber<Int>() {
            override fun onNext(t: Int) {
                Log.d(TAG, t.toString())
            }

            override fun onComplete() {
            }

            override fun onError(t: Throwable) {
            }

        })





        Observable.create(object : OnSubscribe<Int> {
            override fun call(t: Subscriber<in Int>) {
                t.onStart()
                t.onNext(1)
            }
        })
            .map(object : Func1<Int, String> {

                override fun call(t: Int): String {
                    Log.d(TAG, Thread.currentThread().name)
                    return t.toString()
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.main())
            .map(object : Func1<String, Book> {

                override fun call(t: String): Book {
                    Log.d(TAG, Thread.currentThread().name)
                    return Book(t)
                }

            })

            .subscribe(object : Subscriber<Book>() {
                override fun onStart() {

                }

                override fun onNext(t: Book) {
                    Log.d(TAG, Thread.currentThread().name)
                    Log.d(TAG, t.toString())
                }

                override fun onComplete() {
                }

                override fun onError(t: Throwable) {
                    Log.d(TAG, t.message)
                }
            })

    }

    class Book(private val name: String) {
        override fun toString(): String {
            return "book name:$name"
        }
    }
}
