package com.eduramza.mybraziliexapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.NumberFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/********************* String ***************************/
fun String.isBrlTicker() = this.substring(this.lastIndexOf("_") + 1) == "brl"

fun String.marketForUppercase() = this.substringBefore("_").toUpperCase()

/***************** End String ***************************/

/*************** Double and Floats *********************************/

fun Double.transformDoubleInBRL()
        = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)

fun Float.convertFloatToPercent()
        = String.format("%.2f", this).plus("%").replace(",", ".")

/*************** End Double *****************************/