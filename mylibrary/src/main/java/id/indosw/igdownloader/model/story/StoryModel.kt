@file:Suppress("unused")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class StoryModel : Serializable {
    @SerializedName("tray")
    private var tray: ArrayList<TrayModel?>? = null

    @SerializedName("status")
    private var status: String? = null
    fun getTray(): ArrayList<TrayModel?>? {
        return tray
    }

    fun setTray(tray: ArrayList<TrayModel?>?) {
        this.tray = tray
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }
}