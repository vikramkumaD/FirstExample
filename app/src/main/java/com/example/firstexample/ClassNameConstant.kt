package com.example.firstexample

import android.content.Context
import android.net.ConnectivityManager

object ClassNameConstant {

    const val  MODULE_NAME="FirstDynamicModule"

    val MAIN_ACTIVITY: String = "com.example.firstexample.MainActivity"

    val TEST_ACTIVITY: String = "com.example.firstexample.TestActivity"

    public fun isNetworkAvailable(context: Context): Boolean {
        var haveConnectedWifi = false
        var haveConnectedMobile = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = cm!!.allNetworkInfo
        for (ni in netInfo) {
            if (ni.typeName
                    .equals("WIFI", ignoreCase = true)
            ) if (ni.isConnected) haveConnectedWifi = true
            if (ni.typeName
                    .equals("MOBILE", ignoreCase = true)
            ) if (ni.isConnected) haveConnectedMobile = true
        }
        return haveConnectedWifi || haveConnectedMobile
    }
}