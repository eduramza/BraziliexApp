package com.eduramza.mybraziliexapp.ui.listcrypto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.orderbook.Orderbook
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.model.tradehistory.TradeHistory
import com.eduramza.mybraziliexapp.data.repository.PublicRepository
import com.eduramza.mybraziliexapp.ui.isBrlTicker
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.reflect.full.memberProperties

class CurrenciesViewModel(private val publicRepository: PublicRepository) : ViewModel() {

    private val _tickerResponse = MutableLiveData<List<Tickers.TickerUnit>>()
    private val listOfTickers: MutableList<Tickers.TickerUnit> = mutableListOf()
    fun getTickerResponse() = _tickerResponse

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getLoading() = _isLoading

    private val _orderbook = MutableLiveData<Orderbook>()
    fun getOrderbook() = _orderbook

    private val _tradeHistory = MutableLiveData<List<TradeHistory>>()
    fun getTradeHistoryLiveData() = _tradeHistory

    fun getAllTickers(){
        viewModelScope.launch {
            try {
                val result = publicRepository.getAllTickers()
                listOfTickers.clear()
                val properties = result::class.memberProperties

                for (prop in properties){
                    val value = prop.getter.call(result)
                    val unit = value as Tickers.TickerUnit
                    if (unit.active != 0 && unit.market.toString().isBrlTicker()){
                        Log.d("CurrenciesDebug", unit.market!!.substring(unit.market.lastIndexOf("_") + 1))
                        listOfTickers.add(unit)
                    }
                }
            }catch (e: Exception){
                _isLoading.postValue(false)
                e.printStackTrace()
            }
            _isLoading.postValue(false)
            _tickerResponse.postValue(listOfTickers)
        }
    }

    fun getAllOrderbook(market: String){
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                _orderbook.postValue(publicRepository.getOrdersbook(market))
            } catch (e: Exception){
                e.printStackTrace()
                _isLoading.postValue(false)
            }
            _isLoading.postValue(false)
        }
    }

    fun getMarketTradeHistory(market: String){
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                _tradeHistory.postValue(publicRepository.getTradeHistory(market))
            } catch (e: Exception){
                e.printStackTrace()
                _isLoading.postValue(false)
            }
            _isLoading.postValue(false)
        }
    }

}
