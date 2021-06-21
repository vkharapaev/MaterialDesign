package com.headmostlab.materialdesignapp

import android.app.Application
import com.headmostlab.materialdesignapp.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        DI.appComponent = DaggerAppComponent.builder().appContext(this).build()
    }

    companion object {
        lateinit var instance: App
    }
}