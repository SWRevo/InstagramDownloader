@file:Suppress("unused", "PrivatePropertyName", "FunctionName")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable {
    @SerializedName("pk")
    private var pk: Long = 0

    @SerializedName("username")
    private var username: String? = null

    @SerializedName("full_name")
    private var full_name: String? = null

    @SerializedName("is_private")
    private var is_private = false

    @SerializedName("profile_pic_url")
    private var profile_pic_url: String? = null

    @SerializedName("profile_pic_id")
    private var profile_pic_id: String? = null

    @SerializedName("is_verified")
    private var is_verified = false

    @SerializedName("media_count")
    private var media_count = 0

    @SerializedName("follower_count")
    private var follower_count = 0

    @SerializedName("following_count")
    private var following_count = 0

    @SerializedName("biography")
    private var biography: String? = null

    @SerializedName("total_igtv_videos")
    private var total_igtv_videos: String? = null

    @SerializedName("hd_profile_pic_url_info")
    private var hdProfileModel: HDProfileModel? = null

    @SerializedName("mutual_followers_count")
    private var mutual_followers_count = 0

    @SerializedName("profile_context")
    private var profile_context: String? = null
    fun getPk(): Long {
        return pk
    }

    fun setPk(pk: Long) {
        this.pk = pk
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getFull_name(): String? {
        return full_name
    }

    fun setFull_name(full_name: String?) {
        this.full_name = full_name
    }

    fun isIs_private(): Boolean {
        return is_private
    }

    fun setIs_private(is_private: Boolean) {
        this.is_private = is_private
    }

    fun getProfile_pic_url(): String? {
        return profile_pic_url
    }

    fun setProfile_pic_url(profile_pic_url: String?) {
        this.profile_pic_url = profile_pic_url
    }

    fun getProfile_pic_id(): String? {
        return profile_pic_id
    }

    fun setProfile_pic_id(profile_pic_id: String?) {
        this.profile_pic_id = profile_pic_id
    }

    fun isIs_verified(): Boolean {
        return is_verified
    }

    fun setIs_verified(is_verified: Boolean) {
        this.is_verified = is_verified
    }

    fun getMedia_count(): Int {
        return media_count
    }

    fun setMedia_count(media_count: Int) {
        this.media_count = media_count
    }

    fun getFollower_count(): Int {
        return follower_count
    }

    fun setFollower_count(follower_count: Int) {
        this.follower_count = follower_count
    }

    fun getFollowing_count(): Int {
        return following_count
    }

    fun setFollowing_count(following_count: Int) {
        this.following_count = following_count
    }

    fun getBiography(): String? {
        return biography
    }

    fun setBiography(biography: String?) {
        this.biography = biography
    }

    fun getTotal_igtv_videos(): String? {
        return total_igtv_videos
    }

    fun setTotal_igtv_videos(total_igtv_videos: String?) {
        this.total_igtv_videos = total_igtv_videos
    }

    fun getHdProfileModel(): HDProfileModel? {
        return hdProfileModel
    }

    fun setHdProfileModel(hdProfileModel: HDProfileModel?) {
        this.hdProfileModel = hdProfileModel
    }

    fun getMutual_followers_count(): Int {
        return mutual_followers_count
    }

    fun setMutual_followers_count(mutual_followers_count: Int) {
        this.mutual_followers_count = mutual_followers_count
    }

    fun getProfile_context(): String? {
        return profile_context
    }

    fun setProfile_context(profile_context: String?) {
        this.profile_context = profile_context
    }
}