package com.eduramza.mybraziliexapp.data.model.privater


import com.google.gson.annotations.SerializedName

data class Balance(
        @SerializedName("brl")
        val brl: String,
        @SerializedName("btc")
        val btc: String,
        @SerializedName("dash")
        val dash: String,
        @SerializedName("eth")
        val eth: String,
        @SerializedName("ltc")
        val ltc: String,
        @SerializedName("xmr")
        val xmr: String
    )
