package com.eduramza.mybraziliexapp.data.source

import com.eduramza.mybraziliexapp.data.model.currencies.Currencies
import com.eduramza.mybraziliexapp.data.model.orderbook.Orderbook
import com.eduramza.mybraziliexapp.data.model.privater.Balance
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.model.tradehistory.TradeHistory
import retrofit2.http.*

interface BraziliexApi {

    @GET("public/$CURRENCIES_PUBLIC_URL")
    suspend fun callCoins(): Currencies

    @GET("public/$TICKER_PUBLIC_URL")
    suspend fun callTcikers(): Tickers

    @GET("public/$ORDERBOOK_PUBLIC_URL/{market}")
    suspend fun callOrderbook(
        @Path("market") market: String
    ): Orderbook

    @GET("public/$TRADE_HISTORY_PUBLIC_URL/{market}")
    suspend fun callTradeHistory(
        @Path("market") market: String
    ): List<TradeHistory>

    /************** PRIVATE *****************/
    @POST("private/")
    suspend fun callBalance(@Header("Key") key: String,
                            @Header("Sign") sign: String,
                            @Query("command") command: String = "balance"): Balance

}