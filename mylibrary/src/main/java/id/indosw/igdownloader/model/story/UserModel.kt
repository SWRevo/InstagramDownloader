@file:Suppress("unused", "FunctionName", "PrivatePropertyName")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel : Serializable {
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
    private var isFav = 0
    fun getIsFav(): Int {
        return isFav
    }

    fun setIsFav(isFav: Int) {
        this.isFav = isFav
    }

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
}