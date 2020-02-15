package com.eduramza.mybraziliexapp.data.source

import com.eduramza.mybraziliexapp.app.constants.Routes.Companion.ORDERBOOK_PUBLIC_URL
import com.eduramza.mybraziliexapp.app.constants.Routes.Companion.TICKER_PUBLIC_URL
import com.eduramza.mybraziliexapp.app.constants.Routes.Companion.TRADE_HISTORY_PUBLIC_URL
import com.eduramza.mybraziliexapp.data.model.orderbook.Orderbook
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.model.tradehistory.TradeHistory
import retrofit2.http.GET
import retrofit2.http.Path

interface BraziliexApi {

    @GET(TICKER_PUBLIC_URL)
    suspend fun callTcikers(): Tickers

    @GET("$ORDERBOOK_PUBLIC_URL/{market}")
    suspend fun callOrderbook(
        @Path("market") market: String
    ): Orderbook

    @GET("$TRADE_HISTORY_PUBLIC_URL/{market}")
    suspend fun callTradeHistory(
        @Path("market") market: String
    ): List<TradeHistory>

}