@file:Suppress("PrivatePropertyName", "FunctionName", "unused")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FullDetailModel : Serializable {
    @SerializedName("user_detail")
    private var user_detail: UserDetailModel? = null

    @SerializedName("reel_feed")
    private var reel_feed: ReelFeedModel? = null
    fun getUser_detail(): UserDetailModel? {
        return user_detail
    }

    fun setUser_detail(user_detail: UserDetailModel?) {
        this.user_detail = user_detail
    }

    fun getReel_feed(): ReelFeedModel? {
        return reel_feed
    }

    fun setReel_feed(reel_feed: ReelFeedModel?) {
        this.reel_feed = reel_feed
    }
}