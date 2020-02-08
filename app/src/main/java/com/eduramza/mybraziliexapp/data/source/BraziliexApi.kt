package com.eduramza.mybraziliexapp.data.source

import com.eduramza.mybraziliexapp.data.model.currencies.Currencies
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import retrofit2.http.GET

interface BraziliexApi {

    @GET(CURRENCIES_PUBLIC_URL)
    suspend fun callCoins(): Currencies

    @GET(TICKER_PUBLIC_URL)
    suspend fun callTcikers(): Tickers

}