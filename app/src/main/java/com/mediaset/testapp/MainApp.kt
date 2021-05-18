package com.mediaset.testapp

import android.app.Application
import android.content.Context

class MainApp: Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: MainApp? = null

        fun getAppContext(): Context {
            return instance!!.applicationContext
        }
    }
}