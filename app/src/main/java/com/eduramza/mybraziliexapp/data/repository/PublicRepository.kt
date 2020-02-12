package com.eduramza.mybraziliexapp.data.repository

import com.eduramza.mybraziliexapp.data.model.currencies.Currencies
import com.eduramza.mybraziliexapp.data.model.orderbook.Orderbook
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.model.tradehistory.TradeHistory

interface PublicRepository {
    suspend fun getAllCoins(): Currencies
    suspend fun getAllTickers(): Tickers
    suspend fun getOrdersbook(market: String): Orderbook
    suspend fun getTradeHistory(market: String): List<TradeHistory>
}