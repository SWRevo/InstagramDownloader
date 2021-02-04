@file:Suppress("unused")

package id.indosw.igdownloader.util

import android.content.Context
import android.content.SharedPreferences

class SharePrefs(context: Context?) {
    private val sharedPreferences: SharedPreferences? = context!!.getSharedPreferences(PREFERENCE, 0)
    fun putString(key: String?, `val`: String?) {
        sharedPreferences!!.edit().putString(key, `val`).apply()
    }

    fun getString(key: String?): String? {
        return sharedPreferences!!.getString(key, "")
    }

    fun putInt(key: String?, `val`: Int?) {
        sharedPreferences!!.edit().putInt(key, `val`!!).apply()
    }

    fun putBoolean(key: String?, `val`: Boolean?) {
        sharedPreferences!!.edit().putBoolean(key, `val`!!).apply()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreferences!!.getBoolean(key, false)
    }

    fun getInt(key: String?): Int {
        return sharedPreferences!!.getInt(key, 0)
    }

    fun clearSharePrefs() {
        sharedPreferences!!.edit().clear().apply()
    }

    companion object {
        var PREFERENCE: String? = "AllInOneDownloader"
        private var instance: SharePrefs? = null
        @kotlin.jvm.JvmField
        var SESSIONID: String? = "session_id"
        @kotlin.jvm.JvmField
        var USERID: String? = "user_id"
        @kotlin.jvm.JvmField
        var COOKIES: String? = "Cookies"
        @kotlin.jvm.JvmField
        var CSRF: String? = "csrf"
        @kotlin.jvm.JvmField
        var ISINSTALOGIN: String? = "IsInstaLogin"
        @kotlin.jvm.JvmStatic
        fun getInstance(ctx: Context?): SharePrefs? {
            if (instance == null) {
                instance = SharePrefs(ctx)
            }
            return instance
        }
    }

}