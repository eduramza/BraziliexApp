package com.eduramza.mybraziliexapp.ui.listcrypto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.repository.PublicRepository
import com.eduramza.mybraziliexapp.ui.isBrlTicker
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.reflect.full.memberProperties

class ListCryptoViewModel(private val publicRepository: PublicRepository) : ViewModel() {

    init {
        getAllTickers()
    }

    private val _tickerResponse = MutableLiveData<List<Tickers.TickerUnit>>()
    private val listOfTickers: MutableList<Tickers.TickerUnit> = mutableListOf()
    fun getTickerResponse() = _tickerResponse

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getLoading() = _isLoading

    fun getAllTickers(){
        viewModelScope.launch {
            try {
                val result = publicRepository.getAllTickers()
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

}
