package id.indosw.igdownloader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class EdgeSidecarToChildren : Serializable {
    @SerializedName("edges")
    private var edges: MutableList<Edge?>? = null
    fun getEdges(): MutableList<Edge?>? {
        return edges
    }

    fun setEdges(edges: MutableList<Edge?>?) {
        this.edges = edges
    }
}