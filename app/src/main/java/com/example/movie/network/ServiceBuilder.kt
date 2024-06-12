package com.example.movie.network

import retrofit2.Retrofit


/**
 *  Service Util will create a service using retrofit.
 */
object ServiceBuilder {

    private val retrofit: Retrofit = RetrofitBuilder.build()

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}
