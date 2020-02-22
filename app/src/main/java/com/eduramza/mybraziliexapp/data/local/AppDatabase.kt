package com.eduramza.mybraziliexapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eduramza.mybraziliexapp.data.model.local.BuyOrders

@Database(entities = [BuyOrders::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun ordersDao(): BuyOrdersDAO

    companion object{
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "BraziliexApp-Database.db"
        ).build()

    }

}