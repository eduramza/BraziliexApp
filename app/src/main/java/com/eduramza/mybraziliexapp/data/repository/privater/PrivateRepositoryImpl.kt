package com.eduramza.mybraziliexapp.data.repository.privater

import com.eduramza.mybraziliexapp.data.repository.privater.PrivateRepository
import com.eduramza.mybraziliexapp.data.source.BraziliexApi
import com.eduramza.mybraziliexapp.ui.ALGORITM
import com.eduramza.mybraziliexapp.ui.API_KEY
import com.eduramza.mybraziliexapp.ui.API_SECRET
import com.eduramza.mybraziliexapp.ui.AuthPrivate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrivateRepositoryImpl(private val api: BraziliexApi,
                            private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    PrivateRepository {

    override suspend fun callBalance() = withContext(ioDispatcher) {
        AuthPrivate.buildHmacSignature(ALGORITM, API_SECRET)?.let {
            api.callBalance(API_KEY,
                it
            )
        }
    }
}