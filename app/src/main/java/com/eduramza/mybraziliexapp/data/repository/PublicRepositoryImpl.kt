package com.eduramza.mybraziliexapp.data.repository

import com.eduramza.mybraziliexapp.data.source.BraziliexApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PublicRepositoryImpl(private val api: BraziliexApi,
                           private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PublicRepository {

    override suspend fun getAllCoins() = withContext(ioDispatcher) { api.getAllCoins()}

}