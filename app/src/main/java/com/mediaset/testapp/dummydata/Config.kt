package com.mediaset.testapp.dummydata

import android.app.Activity
import android.os.Handler
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bluekai.sdk.BlueKai
import com.bluekai.sdk.listeners.DataPostedListener
import com.mediaset.player_sdk_android.entities.AnalyticsConfigInfo
import com.mediaset.player_sdk_android.entities.PlaybackConfigInfo
import com.mediaset.player_sdk_android.entities.PlaybackType
import com.mediaset.player_sdk_android.entities.PlayerConfig
import com.mediaset.testapp.BuildConfig
import com.mediaset.testapp.MainActivity
import com.mediaset.testapp.MainApp
import com.mediaset.testapp.R
import org.jetbrains.anko.image
import org.json.JSONObject

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

fun getConfig(activity: Activity): PlayerConfig {
    return PlayerConfig(
        null, // objeto de gigya
        null, // PlayerConfig.AdsConfigInfo
//            PlayerConfig.AdsConfigInfo(
//                isActive,
//                showAds,
//                consents // didomi,
//                // descriptionUrl: kotlin.String
//            ), // de atenea -> dpf
        AnalyticsConfigInfo(   //Only sending Bluekai because we have to build new session to test
            AnalyticsConfigInfo.BluekaiConfig(
                AnalyticsConfigInfo.ServiceConfigurationBluekai(
                    isActive = true,
                    bluekaiID = "36882",
                    termConditions = ""
                ),
                objectSession = getBluekaiSession(activity),
                consents = true,
                bluekaiMaps = getBluekaiMaps(),
                userData = AnalyticsConfigInfo.UserDataBluekai(
                    age = "",
                    allowMailing = "0",
                    allowNewsletter = "0",
                    gender = ""
                ),
                sessionStartingTime = 1622044756
            ),
            comscoreConfig = AnalyticsConfigInfo.ComscoreConfig(
                AnalyticsConfigInfo.ServiceConfigurationComscore(
                    isActive = true,
                    c2 = "13184057",
                    appName = "NIUSDIARIO"
                ),
                consents = true
            )
        ), // de atena -> omniture, bluekai, comscore
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
}

fun getBluekaiMaps(): AnalyticsConfigInfo.BluekaiMaps {

    return AnalyticsConfigInfo.BluekaiMaps(
        appInit = getMap("{\"Action\": \"Add\",\n" +
                "\t\t\t\"Category\": \"#genre#\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_model\": \"device_model\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"title\": \"#ooyala#localizable_titles.title_large\",\n" +
                "\t\t\t\"type\": \"#content_type#\",\n" +
                "\t\t\t\"weekday\": \"#day#\"}"),
        appGoToAdvertiser = getMap("{\"Action\": \"goToAdvertiser\",\n" +
                "\t\t\t\"ad_id\": \"#ad_id#\",\n" +
                "\t\t\t\"category\": \"#genre#\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_model\": \"#device_model#\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"title\": \"#title_ad#\",\n" +
                "\t\t\t\"URL\": \"#url#\",\n" +
                "\t\t\t\"weekday\": \"#day#\"}"),
        appShare = getMap("{\"Action\": \"share\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_model\": \"#device_model#\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"Category\": \"#genre#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"Media\": \"#media#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"Title\": \"#ooyala#localizable_titles.title_large\",\n" +
                "\t\t\t\"weekday\": \"#day#\"}"),
        appSkip = getMap("{\"Action\": \"skip\",\n" +
                "\t\t\t\"ad_id\": \"#ad_id#\",\n" +
                "\t\t\t\"Category\": \"#genre#\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_model\": \"#device_model#\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"type\": \"#content_type#\",\n" +
                "\t\t\t\"weekday\": \"#day#\"}"),
        appVideo = getMap("{\"Action\": \"Video\",\n" +
                "\t\t\t\"Category\": \"#genre#\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_model\": \"#device_model#\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"title\": \"#ooyala#localizable_titles.title_large\",\n" +
                "\t\t\t\"type\": \"#content_type#\",\n" +
                "\t\t\t\"weekday\": \"#day#\"}"),
        appRemote = getMap("{\"Action\": \"remote\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_model\": \"#device_model#\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"host_type\": \"#host_type#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"weekday\": \"#day#\",\n" +
                "\t\t\t\"type\": \"#content_type#\",\n" +
                "\t\t\t\"category\": \"#genre#\",\n" +
                "\t\t\t\"title\": \"#ooyala#localizable_titles.title_large\"}"),
        appDownload = getMap("{\"Action\": \"download\",\n" +
                "\t\t\t\"type\": \"vod\",\n" +
                "\t\t\t\"category\": \"#genre#\",\n" +
                "\t\t\t\"title\": \"#ooyala#localizable_titles.title_large\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"device_model\": \"#device_model#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"weekday\": \"#day#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\"}"),
        appStartover = getMap("{\"Action\": \"startover\",\n" +
                "\t\t\t\"type\": \"live\",\n" +
                "\t\t\t\"category\": \"#genre#\",\n" +
                "\t\t\t\"title\": \"#ooyala#localizable_titles.title_large\",\n" +
                "\t\t\t\"id\": \"#user_id#\",\n" +
                "\t\t\t\"OS\": \"#system#\",\n" +
                "\t\t\t\"OSVer\": \"#version#\",\n" +
                "\t\t\t\"device\": \"#device#\",\n" +
                "\t\t\t\"device_vendor\": \"#device_vendor#\",\n" +
                "\t\t\t\"device_model\": \"#device_model#\",\n" +
                "\t\t\t\"origin\": \"#origin#\",\n" +
                "\t\t\t\"sessionTime\": \"#time#\",\n" +
                "\t\t\t\"weekday\": \"#day#\",\n" +
                "\t\t\t\"hour\": \"#hour#\",\n" +
                "\t\t\t\"screenRes\": \"#resolution#\"}")
    )
}

fun getMap(jsonString: String): HashMap<String,String> {

    val map: HashMap<String,String> = hashMapOf()
    val jObject = JSONObject(jsonString)
    val keys = jObject.keys()

    while (keys.hasNext()){
        val key = keys.next()
        val value = jObject.getString(key)
        map[key] = value
    }

    return map
}

fun getBluekaiSession(activity: Activity) : BlueKai {
    return BlueKai.getInstance(activity, MainApp.getAppContext(), false, true, "36880", BuildConfig.VERSION_NAME, (activity as MainActivity), Handler(), true)
}



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


