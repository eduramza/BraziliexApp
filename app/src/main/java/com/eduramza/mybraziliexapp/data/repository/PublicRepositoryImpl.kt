package com.eduramza.mybraziliexapp.data.repository

import com.eduramza.mybraziliexapp.data.source.BraziliexApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PublicRepositoryImpl(private val api: BraziliexApi,
                           private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PublicRepository {

    override suspend fun getAllCoins() = withContext(ioDispatcher) { api.callCoins()}

    override suspend fun getAllTickers() = withContext(ioDispatcher) { api.callTcikers() }

    override suspend fun getOrdersbook(market: String)
            = withContext(ioDispatcher) {api.callOrderbook(market)}

    override suspend fun getTradeHistory(market: String)
            = withContext(ioDispatcher) {api.callTradeHistory(market)}

}