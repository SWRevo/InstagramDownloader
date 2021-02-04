package id.indosw.igdownloader.api

import com.google.gson.JsonObject
import id.indosw.igdownloader.model.story.FullDetailModel
import id.indosw.igdownloader.model.story.StoryModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface APIServices {
    @GET
    fun callResult(@Url Value: String?, @Header("Cookie") cookie: String?, @Header("User-Agent") userAgent: String?): Observable<JsonObject?>?
    @GET
    fun getStoriesApi(@Url Value: String?, @Header("Cookie") cookie: String?, @Header("User-Agent") userAgent: String?): Observable<StoryModel?>?
    @GET
    fun getFullDetailInfoApi(@Url Value: String?, @Header("Cookie") cookie: String?, @Header("User-Agent") userAgent: String?): Observable<FullDetailModel?>?
}