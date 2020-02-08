package com.eduramza.mybraziliexapp.data.repository

import com.eduramza.mybraziliexapp.data.model.Currencies

interface PublicRepository {
    suspend fun getAllCoins(): Currencies
}