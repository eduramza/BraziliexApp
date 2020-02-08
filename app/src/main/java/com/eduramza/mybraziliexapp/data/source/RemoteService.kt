package com.eduramza.mybraziliexapp.data.source

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_PUBLIC_URL = "https://braziliex.com/api/v1/public/"
const val TICKER_PUBLIC_URL = "ticker"
const val CURRENCIES_PUBLIC_URL = "currencies"

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