@file:Suppress("PrivatePropertyName", "FunctionName", "unused")

package id.indosw.igdownloader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ShortcodeMedia : Serializable {
    @SerializedName("display_url")
    private var display_url: String? = null

    @SerializedName("display_resources")
    private var display_resources: MutableList<DisplayResource?>? = null

    @SerializedName("is_video")
    private var is_video = false

    @SerializedName("video_url")
    private var video_url: String? = null

    @SerializedName("edge_sidecar_to_children")
    private var edge_sidecar_to_children: EdgeSidecarToChildren? = null

    @SerializedName("accessibility_caption")
    private var accessibility_caption: String? = null
    fun getDisplay_url(): String? {
        return display_url
    }

    fun setDisplay_url(display_url: String?) {
        this.display_url = display_url
    }

    fun getDisplay_resources(): MutableList<DisplayResource?>? {
        return display_resources
    }

    fun setDisplay_resources(display_resources: MutableList<DisplayResource?>?) {
        this.display_resources = display_resources
    }

    fun isIs_video(): Boolean {
        return is_video
    }

    fun setIs_video(is_video: Boolean) {
        this.is_video = is_video
    }

    fun getVideo_url(): String? {
        return video_url
    }

    fun setVideo_url(video_url: String?) {
        this.video_url = video_url
    }

    fun getEdge_sidecar_to_children(): EdgeSidecarToChildren? {
        return edge_sidecar_to_children
    }

    fun setEdge_sidecar_to_children(edge_sidecar_to_children: EdgeSidecarToChildren?) {
        this.edge_sidecar_to_children = edge_sidecar_to_children
    }

    fun getAccessibility_caption(): String? {
        return accessibility_caption
    }

    fun setAccessibility_caption(accessibility_caption: String?) {
        this.accessibility_caption = accessibility_caption
    }
}