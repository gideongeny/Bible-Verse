package com.mithun.simplebible

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BibleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        MobileAds.initialize(this) {}
        AppOpenManager(this)
    }
}
