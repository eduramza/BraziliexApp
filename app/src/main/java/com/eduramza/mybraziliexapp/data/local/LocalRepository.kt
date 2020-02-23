package com.eduramza.mybraziliexapp.data.local

import com.eduramza.mybraziliexapp.data.model.local.Balance
import kotlinx.coroutines.*

class LocalRepository (val db: AppDatabase,
                       private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO){

    suspend fun insertBalance(balance: Balance){
        db.balanceDAO().insertOrders(balance = balance)
    }

    suspend fun getBalances() = withContext(ioDispatcher)  { db.balanceDAO().getMyOrders() }

    suspend fun updateAmount(coin: String,
                             amount: Double) = withContext(ioDispatcher) {
        db.balanceDAO().updateAmount(newAmount = amount, coin = coin)
    }

    suspend fun updatePrice(coin: String,
                            price: Double) = withContext(ioDispatcher){
        val isUpdate = db.balanceDAO().verifyIfCoinExists(coin)
        if (isUpdate != null) {
            db.balanceDAO().updatePrice(newPrice = price, coin = coin)
        } else {
            db.balanceDAO().insertOrders(Balance(coin = coin, amount = 0.0, unit_price = price))
        }
    }
}