package com.eduramza.mybraziliexapp.data.local

import androidx.room.*
import com.eduramza.mybraziliexapp.data.model.local.Balance

@Dao
interface BalanceDAO{
    @Query("SELECT * FROM balance")
    suspend fun getMyOrders(): List<Balance>

    @Query("SELECT * FROM balance WHERE coin = :coin")
    suspend fun verifyIfCoinExists(coin: String): Balance

    @Insert
    suspend fun insertOrders(balance: Balance): Long

    @Delete
    suspend fun delete(balance: Balance): Int

    @Update
    suspend fun updateOrder(balance: Balance)

    @Query("UPDATE balance SET amount = :newAmount WHERE coin = :coin")
    suspend fun updateAmount(newAmount: Double,
                             coin: String)

    @Query("UPDATE balance SET unit_price = :newPrice WHERE coin = :coin")
    suspend fun updatePrice(newPrice: Double,
                            coin: String)
}