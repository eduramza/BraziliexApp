package com.eduramza.mybraziliexapp.data.local

import com.eduramza.mybraziliexapp.data.model.local.BuyOrders
import kotlinx.coroutines.*

class LocalRepository (val db: AppDatabase,
                       private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO){

    suspend fun insertOrder(orders: BuyOrders){
        db.ordersDao().insertOrders(orders = orders)
    }

    suspend fun getAllOrders() = withContext(ioDispatcher)  { db.ordersDao().getMyOrders() }
}