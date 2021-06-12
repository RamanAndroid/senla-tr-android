package com.example.senlastudy.retrofit.api


import com.example.senlastudy.BuildConfig.API_KEY
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ConfigurationRetrofitObject {
    private var mOkHttpClient: OkHttpClient? = null
    private var mGsonConverter: GsonConverterFactory? = null

    val okHttpClient: OkHttpClient
        get() {
            if (mOkHttpClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .addInterceptor { chain ->
                        var original = chain.request()
                        val url =
                            original.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
                        original = original.newBuilder().url(url).build()
                        chain.proceed(original)
                    }
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                mOkHttpClient = httpBuilder.build()

            }
            return mOkHttpClient!!
        }


    val gsonConverter: GsonConverterFactory
        get() {
            if (mGsonConverter == null) {
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .disableHtmlEscaping()
                            .create()
                    )
            }
            return mGsonConverter!!
        }
}