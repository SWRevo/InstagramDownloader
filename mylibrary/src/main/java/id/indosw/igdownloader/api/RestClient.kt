package id.indosw.igdownloader.api

import android.util.Log
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.*
import java.util.concurrent.TimeUnit

class RestClient private constructor() {
    val service: APIServices
        get() = retrofit!!.create(APIServices::class.java)

    private fun printMsg(msg: String) {
        val chunkCount = msg.length / 4050
        for (i in 0..chunkCount) {
            val max = 4050 * (i + 1)
            if (max >= msg.length) {
                Log.d("Response::", msg.substring(4050 * i))
            } else {
                Log.d("Response::", msg.substring(4050 * i, max))
            }
        }
    }

    companion object {
        private var retrofit: Retrofit? = null
        val instance = RestClient()
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(Interceptor addInterceptor@{ chain: Interceptor.Chain ->
                    var response: Response? = null
                    try {
                        val request = chain.request()
                        response = chain.proceed(request)
                        if (response.code == 200) {
                            try {
                                val jsonObject = JSONObject(response.body!!.string())
                                val data = jsonObject.toString()
                                printMsg(data + "")
                                val contentType = response.body!!.contentType()
                                val responseBody: ResponseBody = data.toResponseBody(contentType)
                                return@addInterceptor response.newBuilder().body(responseBody).build()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    } catch (e: SocketTimeoutException) {
                        e.printStackTrace()
                    }
                    return@addInterceptor response!!
                })
                .addInterceptor(interceptor)
                .build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://www.instagram.com/")
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
        }
    }
}