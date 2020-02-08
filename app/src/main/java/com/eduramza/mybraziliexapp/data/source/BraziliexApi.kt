package com.eduramza.mybraziliexapp.data.source

import com.eduramza.mybraziliexapp.data.model.Currencies
import retrofit2.http.GET

interface BraziliexApi {

    @GET("currencies")
    suspend fun getAllCoins(): Currencies

}