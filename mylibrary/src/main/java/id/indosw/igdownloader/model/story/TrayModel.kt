@file:Suppress("PrivatePropertyName", "unused", "FunctionName")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class TrayModel : Serializable {
    @SerializedName("id")
    private var id: String? = null

    @SerializedName("user")
    private var user: UserModel? = null

    @SerializedName("media_count")
    private var media_count = 0

    @SerializedName("items")
    private var items: ArrayList<ItemModel?>? = null
    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getUser(): UserModel? {
        return user
    }

    fun setUser(user: UserModel?) {
        this.user = user
    }

    fun getMedia_count(): Int {
        return media_count
    }

    fun setMedia_count(media_count: Int) {
        this.media_count = media_count
    }

    fun getItems(): ArrayList<ItemModel?>? {
        return items
    }

    fun setItems(items: ArrayList<ItemModel?>?) {
        this.items = items
    }
}