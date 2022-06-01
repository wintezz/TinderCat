package com.alexpetrov.tinder

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}