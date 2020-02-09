package com.eduramza.mybraziliexapp

import com.eduramza.mybraziliexapp.ui.convertFloatToPercent
import com.eduramza.mybraziliexapp.ui.marketForUppercase
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExampleExtensions {

    @Test
    fun getMarket(){
        val response = "btc_brl"
        val actual = response.marketForUppercase()
        assertEquals("BTC", actual)
    }

    @Test
    fun testTranformBrl(){
        val y = 43.5
        val actual = y.transformDoubleInBRL()
        assertEquals("R$ 43,50", actual)
    }

    @Test
    fun testFloatToPercent(){
        val float = 13.4F
        val actual = float.convertFloatToPercent()
        assertEquals("13.40%", actual)
    }
}