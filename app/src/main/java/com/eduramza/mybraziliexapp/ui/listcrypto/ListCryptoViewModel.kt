package com.eduramza.mybraziliexapp.ui.listcrypto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.currencies.Coins
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.repository.PublicRepository
import com.eduramza.mybraziliexapp.ui.adapter.isBrlTicker
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties

class ListCryptoViewModel(private val publicRepository: PublicRepository) : ViewModel() {

//    private val _response = MutableLiveData<List<Coins>>()
//    private val listCoins : MutableList<Coins> = mutableListOf()
//    fun getResponse() = _response

    init {
        getAllTickers()
    }
//
//    private fun getAllCoins() {
//
//        viewModelScope.launch {
//            val result = publicRepository.getAllCoins()
//            val properties = result::class.memberProperties
//
//            for (prop in properties){
//                val value = prop.getter.call(result)
//                listCoins.add(value as Coins)
//            }
//            _response.postValue(listCoins)
//        }
//    }
//
    private val _tickerResponse = MutableLiveData<List<Tickers.TickerUnit>>()
    private val listOfTickers: MutableList<Tickers.TickerUnit> = mutableListOf()
    fun getTickerResponse() = _tickerResponse

    private fun getAllTickers(){
        viewModelScope.launch {
            val result = publicRepository.getAllTickers()
            val properties = result::class.memberProperties

            for (prop in properties){
                val value = prop.getter.call(result)
                val unit = value as Tickers.TickerUnit
                if (unit.active != 0 && unit.market.toString().isBrlTicker()){
                    Log.d("CurrenciesDebug", unit.market!!.substring(unit.market!!.lastIndexOf("_") + 1))
                    listOfTickers.add(unit)
                }
            }

            _tickerResponse.postValue(listOfTickers)
        }
    }

}
