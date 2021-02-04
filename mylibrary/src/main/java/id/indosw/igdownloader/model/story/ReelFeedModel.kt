@file:Suppress("PrivatePropertyName", "FunctionName", "unused")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ReelFeedModel : Serializable {
    @SerializedName("id")
    private var id: Long = 0

    @SerializedName("latest_reel_media")
    private var latest_reel_media: Long = 0

    @SerializedName("expiring_atexpiring_at")
    private var expiring_at: Long = 0

    @SerializedName("seen")
    private var seen: Long = 0

    @SerializedName("reel_type")
    private var reel_type: String? = null

    @SerializedName("items")
    private var items: ArrayList<ItemModel?>? = null

    @SerializedName("media_count")
    private var media_count = 0
    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getLatest_reel_media(): Long {
        return latest_reel_media
    }

    fun setLatest_reel_media(latest_reel_media: Long) {
        this.latest_reel_media = latest_reel_media
    }

    fun getExpiring_at(): Long {
        return expiring_at
    }

    fun setExpiring_at(expiring_at: Long) {
        this.expiring_at = expiring_at
    }

    fun getSeen(): Long {
        return seen
    }

    fun setSeen(seen: Long) {
        this.seen = seen
    }

    fun getReel_type(): String? {
        return reel_type
    }

    fun setReel_type(reel_type: String?) {
        this.reel_type = reel_type
    }

    fun getItems(): ArrayList<ItemModel?>? {
        return items
    }

    fun setItems(items: ArrayList<ItemModel?>?) {
        this.items = items
    }

    fun getMedia_count(): Int {
        return media_count
    }

    fun setMedia_count(media_count: Int) {
        this.media_count = media_count
    }
}