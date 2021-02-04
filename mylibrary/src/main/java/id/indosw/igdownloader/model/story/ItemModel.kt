@file:Suppress("PrivatePropertyName", "FunctionName", "unused")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ItemModel : Serializable {
    @SerializedName("taken_at")
    private var taken_at: Long = 0

    @SerializedName("pk")
    private var pk: Long = 0

    @SerializedName("id")
    private var id: String? = null

    @SerializedName("device_timestamp")
    private var device_timestamp: Long = 0

    @SerializedName("media_type")
    private var media_type = 0

    @SerializedName("code")
    private var code: String? = null

    @SerializedName("client_cache_key")
    private var client_cache_key: String? = null

    @SerializedName("filter_type")
    private var filter_type = 0

    @SerializedName("image_versions2")
    private var image_versions2: ImageVersionModel? = null

    @SerializedName("original_width")
    private var original_width = 0

    @SerializedName("original_height")
    private var original_height = 0

    @SerializedName("video_versions")
    private var video_versions: ArrayList<VideoVersionModel?>? = null

    @SerializedName("has_audio")
    private var has_audio = false

    @SerializedName("video_duration")
    private var video_duration = 0.0

    @SerializedName("caption_is_edited")
    private var caption_is_edited = false

    @SerializedName("caption_position")
    private var caption_position = 0

    @SerializedName("is_reel_media")
    private var is_reel_media = false

    @SerializedName("photo_of_you")
    private var photo_of_you = false

    @SerializedName("organic_tracking_token")
    private var organic_tracking_token: String? = null

    @SerializedName("expiring_at")
    private var expiring_at: Long = 0

    @SerializedName("can_reshare")
    private var can_reshare = false

    @SerializedName("can_reply")
    private var can_reply = false
    fun getTaken_at(): Long {
        return taken_at
    }

    fun setTaken_at(taken_at: Long) {
        this.taken_at = taken_at
    }

    fun getPk(): Long {
        return pk
    }

    fun setPk(pk: Long) {
        this.pk = pk
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getDevice_timestamp(): Long {
        return device_timestamp
    }

    fun setDevice_timestamp(device_timestamp: Long) {
        this.device_timestamp = device_timestamp
    }

    fun getMedia_type(): Int {
        return media_type
    }

    fun setMedia_type(media_type: Int) {
        this.media_type = media_type
    }

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getClient_cache_key(): String? {
        return client_cache_key
    }

    fun setClient_cache_key(client_cache_key: String?) {
        this.client_cache_key = client_cache_key
    }

    fun getFilter_type(): Int {
        return filter_type
    }

    fun setFilter_type(filter_type: Int) {
        this.filter_type = filter_type
    }

    fun getImage_versions2(): ImageVersionModel? {
        return image_versions2
    }

    fun setImage_versions2(image_versions2: ImageVersionModel?) {
        this.image_versions2 = image_versions2
    }

    fun getOriginal_width(): Int {
        return original_width
    }

    fun setOriginal_width(original_width: Int) {
        this.original_width = original_width
    }

    fun getOriginal_height(): Int {
        return original_height
    }

    fun setOriginal_height(original_height: Int) {
        this.original_height = original_height
    }

    fun getVideo_versions(): ArrayList<VideoVersionModel?>? {
        return video_versions
    }

    fun setVideo_versions(video_versions: ArrayList<VideoVersionModel?>?) {
        this.video_versions = video_versions
    }

    fun isHas_audio(): Boolean {
        return has_audio
    }

    fun setHas_audio(has_audio: Boolean) {
        this.has_audio = has_audio
    }

    fun getVideo_duration(): Double {
        return video_duration
    }

    fun setVideo_duration(video_duration: Double) {
        this.video_duration = video_duration
    }

    fun isCaption_is_edited(): Boolean {
        return caption_is_edited
    }

    fun setCaption_is_edited(caption_is_edited: Boolean) {
        this.caption_is_edited = caption_is_edited
    }

    fun getCaption_position(): Int {
        return caption_position
    }

    fun setCaption_position(caption_position: Int) {
        this.caption_position = caption_position
    }

    fun isIs_reel_media(): Boolean {
        return is_reel_media
    }

    fun setIs_reel_media(is_reel_media: Boolean) {
        this.is_reel_media = is_reel_media
    }

    fun isPhoto_of_you(): Boolean {
        return photo_of_you
    }

    fun setPhoto_of_you(photo_of_you: Boolean) {
        this.photo_of_you = photo_of_you
    }

    fun getOrganic_tracking_token(): String? {
        return organic_tracking_token
    }

    fun setOrganic_tracking_token(organic_tracking_token: String?) {
        this.organic_tracking_token = organic_tracking_token
    }

    fun getExpiring_at(): Long {
        return expiring_at
    }

    fun setExpiring_at(expiring_at: Long) {
        this.expiring_at = expiring_at
    }

    fun isCan_reshare(): Boolean {
        return can_reshare
    }

    fun setCan_reshare(can_reshare: Boolean) {
        this.can_reshare = can_reshare
    }

    fun isCan_reply(): Boolean {
        return can_reply
    }

    fun setCan_reply(can_reply: Boolean) {
        this.can_reply = can_reply
    }
}