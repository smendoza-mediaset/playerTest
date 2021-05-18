package com.mediaset.testapp.dummydata

import com.mediaset.player_sdk_android.entities.UserConfigInfo

val userNoPlusDummy = UserConfigInfo(
    userId = "d6489bafef4549bd8ca6cfd34f93ecf8",//"f228f9121df34a39be61ac8c19e9c5f8",
    signatureTimeStamp = "1614161776",//"1613654835",
    uidSignature = "ISVxu9FUhk3MB+npfZTvWBF9Ix8=",//"2ZU1nNc6zb1vN4K0Ym4vwaD2zfA=",
    isPlusUser = false,
    userSessionId = ""
)

val userPlusDummy = UserConfigInfo(
    userId = "f228f9121df34a39be61ac8c19e9c5f8",
    signatureTimeStamp = "1613654835",
    uidSignature = "2ZU1nNc6zb1vN4K0Ym4vwaD2zfA=",
    isPlusUser = true,
    userSessionId = ""
)