@file:Suppress("unused")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class HDProfileModel : Serializable {
    @SerializedName("width")
    private var width = 0

    @SerializedName("height")
    private var height = 0

    @SerializedName("url")
    private var url: String? = null
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
}