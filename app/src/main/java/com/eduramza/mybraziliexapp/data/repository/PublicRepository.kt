package com.eduramza.mybraziliexapp.data.repository

import com.eduramza.mybraziliexapp.data.model.currencies.Currencies
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers

interface PublicRepository {
    suspend fun getAllCoins(): Currencies
    suspend fun getAllTickers(): Tickers
}