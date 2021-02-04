@file:Suppress("PrivatePropertyName", "FunctionName", "unused")

package id.indosw.igdownloader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DisplayResource : Serializable {
    @SerializedName("src")
    private var src: String? = null

    @SerializedName("config_width")
    private var config_width = 0

    @SerializedName("config_height")
    private var config_height = 0
    fun getSrc(): String? {
        return src
    }

    fun setSrc(src: String?) {
        this.src = src
    }

    fun getConfig_width(): Int {
        return config_width
    }

    fun setConfig_width(config_width: Int) {
        this.config_width = config_width
    }

    fun getConfig_height(): Int {
        return config_height
    }

    fun setConfig_height(config_height: Int) {
        this.config_height = config_height
    }
}