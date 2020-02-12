package com.eduramza.mybraziliexapp.data.model.tradehistory


import com.google.gson.annotations.SerializedName

data class TradeHistory(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("date_exec")
    val dateExec: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("total")
    val total: String,
    @SerializedName("type")
    val type: String
)