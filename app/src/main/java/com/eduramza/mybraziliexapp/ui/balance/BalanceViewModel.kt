package com.eduramza.mybraziliexapp.ui.balance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.local.LocalRepository
import com.eduramza.mybraziliexapp.data.model.local.Balance
import kotlinx.coroutines.launch

class BalanceViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val _balance = MutableLiveData<List<Balance>>()
    fun getBuyOrdersLiveData() = _balance

    fun updateAmount(coin: String, amount: Double){
        viewModelScope.launch {
            try {
                localRepository.updateAmount(coin, amount)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun getAllbalance(){
        viewModelScope.launch {
            try {
                val result = localRepository.getBalances()
                _balance.postValue(result)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

    }


}
