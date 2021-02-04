@file:Suppress("unused")

package id.indosw.igdownloader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseModel : Serializable {
    @SerializedName("graphql")
    private var graphql: Graphql? = null
    fun getGraphql(): Graphql? {
        return graphql
    }

    fun setGraphql(graphql: Graphql?) {
        this.graphql = graphql
    }
}