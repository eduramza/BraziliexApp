package com.eduramza.mybraziliexapp.data.model.orderbook


import com.google.gson.annotations.SerializedName

data class Orderbook(
    @SerializedName("asks")
    val asks: List<Ask>,
    @SerializedName("bids")
    val bids: List<Bid>
) {
    data class Ask(
        @SerializedName("amount")
        val amount: Float,
        @SerializedName("price")
        val price: Double,
        val total: Double?
    )

    data class Bid(
        @SerializedName("amount")
        val amount: Float,
        @SerializedName("price")
        val price: Double,
        val total: Double?
    )
}