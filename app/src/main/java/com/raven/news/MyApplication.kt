package com.raven.news

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        TODO("Not yet implemented")
    }
}