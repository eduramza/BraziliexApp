package com.eduramza.mybraziliexapp.ui.listcrypto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.repository.PublicRepository
import com.eduramza.mybraziliexapp.ui.adapter.isBrlTicker
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties

class ListCryptoViewModel(private val publicRepository: PublicRepository) : ViewModel() {

    init {
        getAllTickers()
    }

    private val _tickerResponse = MutableLiveData<List<Tickers.TickerUnit>>()
    private val listOfTickers: MutableList<Tickers.TickerUnit> = mutableListOf()
    fun getTickerResponse() = _tickerResponse

    fun getAllTickers(){
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
