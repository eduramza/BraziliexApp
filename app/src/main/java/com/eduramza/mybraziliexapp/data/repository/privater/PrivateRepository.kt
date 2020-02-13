package com.eduramza.mybraziliexapp.data.repository.privater

import com.eduramza.mybraziliexapp.data.model.privater.Balance

interface PrivateRepository {
    suspend fun callBalance(): Balance?
}