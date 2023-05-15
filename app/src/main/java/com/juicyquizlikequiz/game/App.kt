package com.juicyquizlikequiz.game

import android.app.Application
import com.onesignal.OneSignal

class App : Application()  {

    private val ONESIGNAL_APP_ID = "8d4efa64-da59-4290-8ecc-75a3b36bb8ad"

    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this)

        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}