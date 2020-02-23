package com.eduramza.mybraziliexapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Balance(
    @PrimaryKey val coin: String,
    val amount: Double?,
    val unit_price: Double)