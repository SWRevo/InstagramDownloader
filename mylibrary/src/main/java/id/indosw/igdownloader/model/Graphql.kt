@file:Suppress("PrivatePropertyName", "FunctionName", "unused")

package id.indosw.igdownloader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Graphql : Serializable {
    @SerializedName("shortcode_media")
    private var shortcode_media: ShortcodeMedia? = null
    fun getShortcode_media(): ShortcodeMedia? {
        return shortcode_media
    }

    fun setShortcode_media(shortcode_media: ShortcodeMedia?) {
        this.shortcode_media = shortcode_media
    }
}