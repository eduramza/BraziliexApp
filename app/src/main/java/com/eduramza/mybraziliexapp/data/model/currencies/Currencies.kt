package com.eduramza.mybraziliexapp.data.model.currencies


import com.eduramza.mybraziliexapp.data.model.currencies.Coins
import com.google.gson.annotations.SerializedName

data class Currencies(
    @SerializedName("abc")
    val abc: Coins,
    @SerializedName("bch")
    val bch: Coins,
    @SerializedName("bnb")
    val bnb: Coins,
    @SerializedName("brl")
    val brl: Coins,
    @SerializedName("brzx")
    val brzx: Coins,
    @SerializedName("bsv")
    val bsv: Coins,
    @SerializedName("btc")
    val btc: Coins,
    @SerializedName("btg")
    val btg: Coins,
    @SerializedName("cfty")
    val cfty: Coins,
    @SerializedName("crw")
    val crw: Coins,
    @SerializedName("dash")
    val dash: Coins,
    @SerializedName("dcr")
    val dcr: Coins,
    @SerializedName("eos")
    val eos: Coins,
    @SerializedName("epc")
    val epc: Coins,
    @SerializedName("etc")
    val etc: Coins,
    @SerializedName("eth")
    val eth: Coins,
    @SerializedName("gmr")
    val gmr: Coins,
    @SerializedName("gnt")
    val gnt: Coins,
    @SerializedName("iop")
    val iop: Coins,
    @SerializedName("lcc")
    val lcc: Coins,
    @SerializedName("ltc")
    val ltc: Coins,
    @SerializedName("mxt")
    val mxt: Coins,
    @SerializedName("nbr")
    val nbr: Coins,
    @SerializedName("omg")
    val omg: Coins,
    @SerializedName("onix")
    val onix: Coins,
    @SerializedName("prsp")
    val prsp: Coins,
    @SerializedName("smart")
    val smart: Coins,
    @SerializedName("sngls")
    val sngls: Coins,
    @SerializedName("trx")
    val trx: Coins,
    @SerializedName("tusd")
    val tusd: Coins,
    @SerializedName("usdt")
    val usdt: Coins,
    @SerializedName("xmr")
    val xmr: Coins,
    @SerializedName("xrp")
    val xrp: Coins,
    @SerializedName("zec")
    val zec: Coins,
    @SerializedName("zrx")
    val zrx: Coins
)