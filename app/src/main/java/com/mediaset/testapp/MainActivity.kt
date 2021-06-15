package com.mediaset.testapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.bluekai.sdk.listeners.DataPostedListener
import com.google.android.exoplayer2.ui.PlayerView
import com.mediaset.player_sdk_android.VideoPlayerSdk
import com.mediaset.player_sdk_android.entities.*
import com.mediaset.player_sdk_android.sdk_delegate.PlayerSdkListener
import com.mediaset.testapp.dummydata.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PlayerSdkListener, DataPostedListener {

    private var playerSdk: VideoPlayerSdk? = null

    private var config = getConfig(this)

    private var showingControls = false



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

            loadplayer.visibility = View.GONE
        }else {

            loadplayer.visibility = View.VISIBLE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VideoPlayerSdk.listener = this

        playerSdk = VideoPlayerSdk.Builder(this)
            .setPlayerContainerView(player_container)
            .build()



        loadplayer.setOnClickListener {

            seek_bar.visibility = View.GONE
            playerSdk?.initVideoPlayerSDK(config,seek_bar)
        }

        if(showingControls) showhideControls.text = "Hide controls" else "Show controls"

        showhideControls.setOnClickListener {

            showingControls = !showingControls
            playerSdk?.forceShowHideCustomControls(showingControls)
        }

        forceClose.setOnClickListener {

            playerSdk?.forceClosePlayer()
        }



    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event?.action == MotionEvent.ACTION_DOWN){

            showingControls = !showingControls
            playerSdk?.forceShowHideCustomControls(showingControls)
        }

        return super.onTouchEvent(event)
    }

    override fun onOffersPermissionCheck(
        contentId: String,
        onPermission: (Boolean, OfferType) -> Unit
    ) {

        onPermission(true,OfferType.FREE)
    }

    override fun onParentalControlPermissionCheck(
        eventRating: String,
        onSuccess: () -> Unit,
        onError: () -> Unit,
        fragmentManager: FragmentManager?
    ) {

        onSuccess()
    }

    override fun onUserInfoCheck(onUserInfoResponse: (UserConfigInfo?) -> Unit) {

        onUserInfoResponse(null)
    }

    override fun onVodPreplayerCheck(
        contentId: String,
        onResponse: (PlaybackConfigInfo.MediasetVideo) -> Unit
    ) {

    }

    override fun onLivePreplayerCheck(
        url: String,
        onResponse: (PlaybackConfigInfo.MediasetVideo) -> Unit
    ) {

    }



    override fun onPremiumContentCheck(contentId: String) = true

    override fun onExclusiveContentCheck(contentId: String) = true

    override fun onRecommendedParamValueCheck(contentId: String) = ""

    override fun onABTestParamValueCheck() = ""

    override fun onPlayerPrepared(view: PlayerView) {

    }

    override fun onContentPlaybackStateChanged(isPlaying: Boolean) {

    }

    override fun onPlaybackInit() {
        info_label.text = "Playback initialized"
    }

    override fun onPlaybackEnd() {
        info_label.text = "Playback finished"
    }

    override fun onPlaybackError(message: String) {

    }

    override fun onEventChanged() {

    }

    override fun onAdsInit() {

        info_label.text = "Ads initialized"
    }

    override fun onAdsEnd() {

        info_label.text = "Ads Finished"
    }

    override fun onCerberoTokenUpdate(token: String) {

    }

    override fun onHigherContentRatingAllowedUpdate(rating: String) {

    }

    override fun onPlayPauseClicked(play: Boolean) {

    }

    override fun onBackwardSeek() {

        Log.d("MainActivity", "Seek Back")
    }

    override fun onForwardSeek() {

        Log.d("MainActivity", "Seek Forward")
    }

    override fun onPIPInit() {

    }

    override fun onShareButtonClicked(
        sharedInfo: SharedInfo,
        launchingActivity: FragmentActivity?
    ) {

    }

    override fun onPlayerClose() {

    }

    override fun onChromecastInit() {

    }

    override fun onLoadRemoteToChromecast(chromecastInfo: ChromecastInfo) {

    }

    override fun onNextVideoRequested() {

    }

    override fun onVideoControllersVisibilityChange(isShow: Boolean) {

        seek_bar.visibility = if(isShow) View.VISIBLE else View.GONE
        showhideControls.text = if (isShow) "Hide controls" else "Show controls"
        Log.d("Controllers", if(isShow) "Displayed" else "Hidden")
    }

    override fun onDataPosted(p0: Boolean, p1: String?) {
        Log.d("MainActivity", "$p1")
    }


}