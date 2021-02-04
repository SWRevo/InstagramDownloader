@file:Suppress("PrivatePropertyName", "unused", "FunctionName")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CandidatesModel : Serializable {
    @SerializedName("width")
    private var width = 0

    @SerializedName("height")
    private var height = 0

    @SerializedName("url")
    private var url: String? = null

    @SerializedName("scans_profile")
    private var scans_profile: String? = null
    fun getWidth(): Int {
        return width
    }

    fun setWidth(width: Int) {
        this.width = width
    }

    fun getHeight(): Int {
        return height
    }

    fun setHeight(height: Int) {
        this.height = height
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getScans_profile(): String? {
        return scans_profile
    }

    fun setScans_profile(scans_profile: String?) {
        this.scans_profile = scans_profile
    }
}