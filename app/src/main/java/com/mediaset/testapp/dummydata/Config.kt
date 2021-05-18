package com.mediaset.testapp.dummydata

import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.mediaset.player_sdk_android.entities.PlaybackConfigInfo
import com.mediaset.player_sdk_android.entities.PlaybackType
import com.mediaset.player_sdk_android.entities.PlayerConfig
import com.mediaset.testapp.MainApp
import com.mediaset.testapp.R
import org.jetbrains.anko.image

val playerVodConfig = PlayerConfig(
    null, // objeto de gigya
    null, // PlayerConfig.AdsConfigInfo
//            PlayerConfig.AdsConfigInfo(
//                isActive,
//                showAds,
//                consents // didomi,
//                // descriptionUrl: kotlin.String
//            ), // de atenea -> dpf
    null, // de atena -> omniture, bluekai, comscore
    null, // null
    null, // null
    PlaybackConfigInfo(
        PlaybackType.VOD,
        PlaybackConfigInfo.UIConfig(
            listOf(), // TODO topButtons: List<PlaybackConfigInfo.UIConfig.TopButtonsInfo>,
            ConfigUtils.getPlaybackButtons(false),
            false
        ),
        PlaybackConfigInfo.Environment.DEBUG, // TODO
        PlaybackConfigInfo.UrlContent(
            "", // no necesario
            "es.mediaset.nius",
            "app.nius.android.es", //[android | ios]
            "baseUrl" // -> pre: https://www.niusdiario.pre, pro: https://www.niusdiario.es
        ),
        PlaybackConfigInfo.MediasetVideo(
            "https://malaactitud.pre.3d99a4cb092e6d20ff56d80b9efd9a8f8802ec81.es:8605/1.0.0/get?oid=bitban_api&eid=%2Fapi%2Fcms%2Fniusdiario%2Fvideos%2FMDSVOD20210428_0004%2Fconfig%2Ffinal.json%3Fplatform%3Dapp-native",
            "dataEpisodeName", // "title" de primer nivel del json
            false, // no necesario
            null, // no necesario
            null, // no necesario
            null, // no necesario
            false // no necesario
        ),
        PlaybackConfigInfo.ServicesConfig(
            PlaybackConfigInfo.ServicesConfig.SessionCheckConfig(
                false, // no necesario
                "", // no necesario
                0 // no necesario
            ),
            PlaybackConfigInfo.ServicesConfig.NextEpisodeConfig(
                false, // no necesario
                0 // no necesario
            )
        )
    )
)

val playerLiveConfig = PlayerConfig(
    null, // objeto de gigya
    null, // PlayerConfig.AdsConfigInfo
//            PlayerConfig.AdsConfigInfo(
//                isActive,
//                showAds,
//                consents // didomi,
//                // descriptionUrl: kotlin.String
//            ), // de atenea -> dpf
    null, // de atena -> omniture, bluekai, comscore
    null, // null
    null, // null
    PlaybackConfigInfo(
        PlaybackType.LIVE,
        PlaybackConfigInfo.UIConfig(
            listOf(), // TODO topButtons: List<PlaybackConfigInfo.UIConfig.TopButtonsInfo>,
            ConfigUtils.getPlaybackButtons(false),
            false
        ),
        PlaybackConfigInfo.Environment.DEBUG, // TODO
        PlaybackConfigInfo.UrlContent(
            "", // no necesario
            "es.mediaset.nius",
            "app.nius.android.es", //[android | ios]
            "baseUrl" // -> pre: https://www.niusdiario.pre, pro: https://www.niusdiario.es
        ),
        PlaybackConfigInfo.MediasetVideo(
            "https://malaactitud.pre.3d99a4cb092e6d20ff56d80b9efd9a8f8802ec81.es:8605/1.0.0/get?oid=bitban_api&eid=%2Fapi%2Fcms%2Fniusdiario%2Fvideos%2FMDSVST20200617_0003%2Fconfig%2Ffinal.json%3Fplatform%3Dapp-native",
            "dataEpisodeName", // "title" de primer nivel del json
            false, // no necesario
            null, // no necesario
            null, // no necesario
            null, // no necesario
            false // no necesario
        ),
        PlaybackConfigInfo.ServicesConfig(
            PlaybackConfigInfo.ServicesConfig.SessionCheckConfig(
                false, // no necesario
                "", // no necesario
                0 // no necesario
            ),
            PlaybackConfigInfo.ServicesConfig.NextEpisodeConfig(
                false, // no necesario
                0 // no necesario
            )
        )
    )
)

class ConfigUtils {

    companion object{

        fun getPlaybackButtons(isLive: Boolean): List<PlaybackConfigInfo.UIConfig.PlaybackButtonInfo> {

            val playBtn = ImageView(MainApp.getAppContext())
            playBtn.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            playBtn.image = ContextCompat.getDrawable(MainApp.getAppContext(), R.drawable.play_btn)

            val pauseBtn = ImageView(MainApp.getAppContext())
            pauseBtn.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            pauseBtn.image = ContextCompat.getDrawable(MainApp.getAppContext(), R.drawable.pause_btn)

            val rwdBtn = ImageView(MainApp.getAppContext())
            rwdBtn.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            rwdBtn.image = ContextCompat.getDrawable(MainApp.getAppContext(), R.drawable.ic_replay_30)

            val fwdBtn = ImageView(MainApp.getAppContext())
            fwdBtn.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            fwdBtn.image = ContextCompat.getDrawable(MainApp.getAppContext(), R.drawable.ic_forward_30)


            return if(isLive) listOf(
                PlaybackConfigInfo.UIConfig.PlaybackButtonInfo(playBtn, PlaybackConfigInfo.UIConfig.PlaybackButtonsType.PLAY),
                PlaybackConfigInfo.UIConfig.PlaybackButtonInfo(pauseBtn, PlaybackConfigInfo.UIConfig.PlaybackButtonsType.PAUSE)
            )
            else
                listOf (
                    PlaybackConfigInfo.UIConfig.PlaybackButtonInfo(rwdBtn, PlaybackConfigInfo.UIConfig.PlaybackButtonsType.RWD, 10),
                    PlaybackConfigInfo.UIConfig.PlaybackButtonInfo(playBtn, PlaybackConfigInfo.UIConfig.PlaybackButtonsType.PLAY),
                    PlaybackConfigInfo.UIConfig.PlaybackButtonInfo(pauseBtn, PlaybackConfigInfo.UIConfig.PlaybackButtonsType.PAUSE),
                    PlaybackConfigInfo.UIConfig.PlaybackButtonInfo(fwdBtn, PlaybackConfigInfo.UIConfig.PlaybackButtonsType.FWD, 10)
                )
        }
    }

}


