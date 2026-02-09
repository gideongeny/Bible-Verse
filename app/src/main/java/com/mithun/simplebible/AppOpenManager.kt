package com.mithun.simplebible

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import java.util.*

class AppOpenManager(private val myApplication: BibleApplication) : DefaultLifecycleObserver, Application.ActivityLifecycleCallbacks {

    private var appOpenAd: AppOpenAd? = null
    private var loadCallback: AppOpenAd.AppOpenAdLoadCallback? = null
    private var currentActivity: Activity? = null
    private var loadTime: Long = 0

    // Ad Unit IDs for App Open Ads
    private val AD_UNIT_ID_1 = "ca-app-pub-1281448884303417/6887744767"
    private val AD_UNIT_ID_2 = "ca-app-pub-1281448884303417/9689158181"

    init {
        this.myApplication.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    fun fetchAd() {
        if (isAdAvailable()) {
            return
        }

        loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                loadTime = Date().time
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                // Try second ID if first fails
                fetchAdWithId(AD_UNIT_ID_2)
            }
        }
        val request = getAdRequest()
        AppOpenAd.load(myApplication, AD_UNIT_ID_1, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback as AppOpenAd.AppOpenAdLoadCallback)
    }

    private fun fetchAdWithId(adUnitId: String) {
        val loadCallback2 = object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                loadTime = Date().time
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {}
        }
        AppOpenAd.load(myApplication, adUnitId, getAdRequest(), AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback2)
    }

    private fun getAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    fun showAdIfAvailable() {
        if (isAdAvailable()) {
            val fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    appOpenAd = null
                    fetchAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: com.google.android.gms.ads.AdError) {}

                override fun onAdShowedFullScreenContent() {}
            }

            appOpenAd?.fullScreenContentCallback = fullScreenContentCallback
            currentActivity?.let {
                appOpenAd?.show(it)
            }
        } else {
            fetchAd()
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        showAdIfAvailable()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }
}
