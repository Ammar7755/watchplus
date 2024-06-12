package com.example.movie.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *  RetrofitBuilder used to build a retrofit instance and add okhttp client for it.
 */
object RetrofitBuilder {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_PARAM = "api_key"
    const val API_KEY_VALUE = "888198ca14b6af7699f19a689cb028ea"
    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // will convert json response to your object response
            .client(getOkHttpClient())
            .build()
    }

    // return okhttpClient
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor(Interceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        .addHeader("accept", "application/json")
                        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4ODgxOThjYTE0YjZhZjc2OTlmMTlhNjg5Y2IwMjhlYSIsInN1YiI6IjY2NGVlZGZjYWY2MjA5ODQzMWJiMzNhZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.CIQkdrZMEWbd1_T-nCsF6JnksPYtsBsblAsd7q8U7Cw")
                        .addHeader(API_KEY_PARAM, API_KEY_VALUE).build()
                chain.proceed(request)
            })
            .connectTimeout(10000L, TimeUnit.MILLISECONDS)
            .readTimeout(10000L, TimeUnit.MILLISECONDS)
            .writeTimeout(10000L, TimeUnit.MILLISECONDS).build()
    }
}