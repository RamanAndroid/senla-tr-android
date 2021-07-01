package com.example.senlastudy

import android.app.Application
import com.example.senlastudy.retrofit.api.ApiMovie
import com.example.senlastudy.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class MovieApplication : Application() {
    companion object {
        lateinit var apiService: ApiMovie
            private set

        lateinit var okHttpClient: OkHttpClient
            private set

        lateinit var gsonConverter: GsonConverterFactory
            private set

        lateinit var localLanguage: String
            private set
    }

    override fun onCreate() {
        super.onCreate()
        getDefaultLanguage()
        createGsonConverter()
        createOkHttpClient()
        createApiService()

    }

    private fun createApiService() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverter)
            .client(okHttpClient)
            .build()
        apiService = retrofit.create(ApiMovie::class.java)
    }

    private fun createOkHttpClient() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpBuilder = OkHttpClient.Builder()
        httpBuilder
            .addInterceptor { chain ->
                var original = chain.request()
                val url =
                    original.url.newBuilder()
                        .addQueryParameter(
                            "api_key",
                            BuildConfig.API_KEY
                        )
                        .addQueryParameter(
                            "language",
                            localLanguage
                        ).build()
                original = original.newBuilder().url(url).build()
                chain.proceed(original)
            }
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        okHttpClient = httpBuilder.build()

    }

    private fun createGsonConverter() {
        gsonConverter = GsonConverterFactory
            .create(
                GsonBuilder()
                    .setLenient()
                    .disableHtmlEscaping()
                    .create()
            )
    }

    private fun getDefaultLanguage() {
        localLanguage = Locale.getDefault().language
    }
}
