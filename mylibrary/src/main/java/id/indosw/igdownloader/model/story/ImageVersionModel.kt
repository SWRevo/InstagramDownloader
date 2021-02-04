@file:Suppress("unused")

package id.indosw.igdownloader.model.story

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ImageVersionModel : Serializable {
    @SerializedName("candidates")
    private var candidates: ArrayList<CandidatesModel?>? = null
    fun getCandidates(): ArrayList<CandidatesModel?>? {
        return candidates
    }

    fun setCandidates(candidates: ArrayList<CandidatesModel?>?) {
        this.candidates = candidates
    }
}