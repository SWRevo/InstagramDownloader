@file:Suppress("LocalVariableName")

package id.indosw.igdownloader.api

import android.annotation.SuppressLint
import com.google.gson.JsonObject
import id.indosw.igdownloader.model.story.FullDetailModel
import id.indosw.igdownloader.model.story.StoryModel
import id.indosw.igdownloader.util.Utils.Companion.isNullOrEmpty
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CommonClassForAPI {
    fun callResult(observer: DisposableObserver<JsonObject>, URL: String?, Cookie: String?) {
        @Suppress("NAME_SHADOWING") var Cookie = Cookie
        if (isNullOrEmpty(Cookie)) {
            Cookie = ""
        }
        RestClient.instance.service.callResult(
                URL, Cookie,
                "Instagram 9.5.2 (iPhone7,2; iPhone OS 9_3_3; en_US; en-US; scale=2.00; 750x1334) AppleWebKit/420+"
        )
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<JsonObject?> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(o: JsonObject) {
                        @Suppress("UNREACHABLE_CODE", "CAST_NEVER_SUCCEEDS")
                        observer.onNext(o)
                    }

                    override fun onError(e: Throwable) {
                        observer.onError(e)
                    }

                    override fun onComplete() {
                        observer.onComplete()
                    }
                })
    }

    fun getStories(observer: DisposableObserver<StoryModel>, Cookie: String?) {
        @Suppress("NAME_SHADOWING") var Cookie = Cookie
        if (isNullOrEmpty(Cookie)) {
            Cookie = ""
        }
        RestClient.instance.service.getStoriesApi(
                "https://i.instagram.com/api/v1/feed/reels_tray/", Cookie,
                "\"Instagram 9.5.2 (iPhone7,2; iPhone OS 9_3_3; en_US; en-US; scale=2.00; 750x1334) AppleWebKit/420+\""
        )
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<StoryModel?> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(o: StoryModel) {
                        @Suppress("UNREACHABLE_CODE", "CAST_NEVER_SUCCEEDS")
                        observer.onNext(o)
                    }

                    override fun onError(e: Throwable) {
                        observer.onError(e)
                    }

                    override fun onComplete() {
                        observer.onComplete()
                    }
                })
    }

    fun getFullDetailFeed(observer: DisposableObserver<FullDetailModel>, UserId: String, Cookie: String?) {
        RestClient.instance.service.getFullDetailInfoApi(
                "https://i.instagram.com/api/v1/users/$UserId/full_detail_info?max_id=",
                Cookie,
                "\"Instagram 9.5.2 (iPhone7,2; iPhone OS 9_3_3; en_US; en-US; scale=2.00; 750x1334) AppleWebKit/420+\""
        )
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<FullDetailModel?> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(o: FullDetailModel) {
                        @Suppress("UNREACHABLE_CODE", "CAST_NEVER_SUCCEEDS")
                        observer.onNext(o)
                    }

                    override fun onError(e: Throwable) {
                        observer.onError(e)
                    }

                    override fun onComplete() {
                        observer.onComplete()
                    }
                })
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var CommonClassForAPI: CommonClassForAPI? = null
        @kotlin.jvm.JvmStatic
        fun getInstance(): CommonClassForAPI? {
            if (CommonClassForAPI == null) {
                CommonClassForAPI = CommonClassForAPI()
            }
            return CommonClassForAPI
        }
    }
}