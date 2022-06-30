package com.example.demod.RXCalling


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Anil Tiwari on 15/12/2021.
 */

object ApiClient {

    private var retrofit: Retrofit? = null
    private val REQUEST_TIMEOUT: Long = 30
    private val WRITE_TIMEOUT: Long = 15
    private var okHttpClient: OkHttpClient? = null

    val client: Retrofit?
        get() {

            if (okHttpClient == null)
                initOkHttp()
            okHttpClient?.dispatcher()?.cancelAll()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(ServiceInterface.BASE_URL)
                        .client(okHttpClient!!)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }



    private fun initOkHttp() {
        val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        okHttpClient = httpClient.build()
    }
}