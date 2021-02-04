@file:Suppress("unused")

package id.indosw.igdownloader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Edge : Serializable {
    @SerializedName("node")
    private var node: Node? = null
    fun getNode(): Node? {
        return node
    }

    fun setNode(node: Node?) {
        this.node = node
    }
}