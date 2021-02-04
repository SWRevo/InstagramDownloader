@file:Suppress("unused")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserDetailModel : Serializable {
    @SerializedName("user")
    private var user: User? = null
    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }
}