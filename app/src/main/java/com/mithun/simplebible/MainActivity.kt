package com.mithun.simplebible

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mithun.simplebible.utilities.Prefs
import com.mithun.simplebible.utilities.gone
import com.mithun.simplebible.utilities.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var mInterstitialAd: InterstitialAd? = null

    @Inject
    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemReselectedListener {
            // empty. Prevents recreating fragment when same menu item is clicked multiple times
        }
        initUI(navView)
        loadAds()
    }

    private fun loadAds() {
        // Load Banner Ad
        val adView: AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        // Load Interstitial Ad
        InterstitialAd.load(
            this,
            "ca-app-pub-1281448884303417/5428049782",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            }
        )
    }

    fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
            loadAds() // Reload for next time
        }
    }

    private fun initUI(navView: BottomNavigationView) {
        initMode()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_add_edit_note,
                R.id.navigation_filter,
                R.id.navigation_image_select,
                R.id.navigation_image_share,
                R.id.navigation_image_edit,
                R.id.navigation_book_select -> {
                    // hide the bottom nav bar if it hits any of these destinations
                    navView.gone
                }
                else -> {
                    navView.visible
                    // Optionally show interstitial on some destination changes
                    if (destination.id == R.id.navigation_chapter_verses || destination.id == R.id.navigation_books) {
                        showInterstitial()
                    }
                }
            }
        }
    }

    // initialize with the correct mode
    private fun initMode() {
        delegate.localNightMode = if (prefs.isNightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
    }
}
