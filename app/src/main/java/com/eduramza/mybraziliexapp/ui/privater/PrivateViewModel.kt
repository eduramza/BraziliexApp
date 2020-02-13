package com.eduramza.mybraziliexapp.ui.privater

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.privater.Balance
import com.eduramza.mybraziliexapp.data.repository.privater.PrivateRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class PrivateViewModel(private val privateRepository: PrivateRepository) : ViewModel() {

    private val _balance = MutableLiveData<Balance>()
    fun getBalanceLiveData() = _balance

    fun callBalance(){
        try {
            viewModelScope.launch {
                val balanceR = privateRepository.callBalance()
                _balance.postValue(balanceR)
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}
