package com.eduramza.mybraziliexapp.ui.balance

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.local.LocalRepository
import com.eduramza.mybraziliexapp.data.model.local.BuyOrders
import kotlinx.coroutines.launch

class BalanceViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val listOfOrders: MutableList<BuyOrders> = mutableListOf()
    private val _buyOrders = MutableLiveData<List<BuyOrders>>()
    fun getBuyOrdersLiveData() = _buyOrders

    fun insertNewOrder(orders: BuyOrders){
        viewModelScope.launch {
            try {
                localRepository.insertOrder(orders)
                listOrders()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun listOrders(){
        viewModelScope.launch {
            try {
                _buyOrders.postValue(localRepository.getAllOrders())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

    }


}
