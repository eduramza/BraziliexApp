package com.eduramza.mybraziliexapp.data.source

import com.eduramza.mybraziliexapp.app.constants.Routes.Companion.BASE_PUBLIC_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteService{

    fun getRemote(): BraziliexApi {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(LoggingInterceptor())

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_PUBLIC_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        return retrofit.create(BraziliexApi::class.java)
    }

}