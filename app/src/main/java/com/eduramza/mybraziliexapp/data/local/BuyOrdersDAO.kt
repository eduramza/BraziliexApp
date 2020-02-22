package com.eduramza.mybraziliexapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eduramza.mybraziliexapp.data.model.local.BuyOrders

@Dao
interface BuyOrdersDAO{
    @Query("SELECT * FROM buyorders")
    suspend fun getMyOrders(): List<BuyOrders>

    @Insert
    suspend fun insertOrders(orders: BuyOrders): Long

    @Delete
    suspend fun delete(orders: BuyOrders): Int
}