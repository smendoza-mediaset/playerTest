package com.mediaset.testapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.exoplayer2.ui.PlayerView
import com.mediaset.player_sdk_android.VideoPlayerSdk
import com.mediaset.player_sdk_android.entities.*
import com.mediaset.player_sdk_android.sdk_delegate.PlayerSdkListener
import com.mediaset.testapp.dummydata.playerVodConfig
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PlayerSdkListener {

    private var playerSdk: VideoPlayerSdk? = null

    private var config = playerVodConfig

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

            playerSdk?.initVideoPlayerSDK(config)
        }

        if(showingControls) showhideControls.text = "Hide controls" else "Show controls"

        showhideControls.setOnClickListener {

            showingControls = !showingControls
            playerSdk?.forceShowHideCustomControls(showingControls)
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
        //info_label.text = "Ads initialized"
    }

    override fun onAdsEnd() {
        //info_label.text = "Ads finished"
    }

    override fun onCerberoTokenUpdate(token: String) {

    }

    override fun onHigherContentRatingAllowedUpdate(rating: String) {

    }

    override fun onPlayPauseClicked(play: Boolean) {

    }

    override fun onBackwardSeek() {

    }

    override fun onForwardSeek() {

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

        showhideControls.text = if (isShow) "Hide controls" else "Show controls"
        Log.d("Controllers", if(isShow) "Displayed" else "Hidden")
    }


}