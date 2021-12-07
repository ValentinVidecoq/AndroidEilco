package com.example.newslist

import android.app.Application

class NewsListApplication : Application() {
    var login: String? = null

    override fun onCreate() {
        super.onCreate()
        this.login = null
    }
}