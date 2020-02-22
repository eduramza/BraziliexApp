package com.eduramza.mybraziliexapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BuyOrders(
    @PrimaryKey(autoGenerate = true) val oid: Int,
    var coin: String,
    var amount: Double,
    var unit_price: Double?,
    var total: Double
)