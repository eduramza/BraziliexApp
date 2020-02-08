package com.eduramza.mybraziliexapp.data.model.currencies


import com.google.gson.annotations.SerializedName

data class Coins(
    @SerializedName("active")
    val active: Int,
    @SerializedName("decimal")
    val decimal: Int,
    @SerializedName("decimal_withdrawal")
    val decimalWithdrawal: Int,
    @SerializedName("dev_active")
    val devActive: Int,
    @SerializedName("gateway")
    val gateway: Int,
    @SerializedName("is_deposit_active")
    val isDepositActive: Int,
    @SerializedName("is_fiat")
    val isFiat: Int,
    @SerializedName("is_token_erc20")
    val isTokenErc20: Int,
    @SerializedName("is_withdrawal_active")
    val isWithdrawalActive: Int,
    @SerializedName("minAmountTradeBTC")
    val minAmountTradeBTC: Double,
    @SerializedName("minAmountTradeFIAT")
    val minAmountTradeFIAT: Float,
    @SerializedName("minAmountTradeUSDT")
    val minAmountTradeUSDT: Double,
    @SerializedName("minConf")
    val minConf: Float,
    @SerializedName("minDeposit")
    val minDeposit: Float,
    @SerializedName("MinWithdrawal")
    val minWithdrawal: Float,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: String,
    @SerializedName("txDepositFee")
    val txDepositFee: Float,
    @SerializedName("txDepositPercentageFee")
    val txDepositPercentageFee: Float,
    @SerializedName("txWithdrawalFee")
    val txWithdrawalFee: Float,
    @SerializedName("under_maintenance")
    val underMaintenance: Int,
    @SerializedName("unlisted")
    val unlisted: Int,
    @SerializedName("withdrawal_txFee")
    val withdrawalTxFee: Double
)