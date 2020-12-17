package com.thesame.baselibrary.response

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 *Created by whz  on 2019-06-24
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {
        interceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Contern-Type", "application/json")
                .addHeader("charset", "utf-8")
                /* .addHeader("clientType", "0")
                 .addHeader("userId", AppPrefsUtils.getString(USER_ID))
                 .addHeader("language", AppPrefsUtils.getString(ISLANGUAGE))*/
                .build()
            chain.proceed(request)
        }
        retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.BaseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()) //支持RxJava2
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(initLoginInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun initLoginInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}