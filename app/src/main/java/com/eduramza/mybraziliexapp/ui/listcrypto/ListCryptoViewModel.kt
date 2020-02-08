package com.eduramza.mybraziliexapp.ui.listcrypto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.Coins
import com.eduramza.mybraziliexapp.data.model.Currencies
import com.eduramza.mybraziliexapp.data.repository.PublicRepository
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties

class ListCryptoViewModel(private val publicRepository: PublicRepository) : ViewModel() {

    private val _response = MutableLiveData<List<Coins>>()
    private val listCoins : MutableList<Coins> = mutableListOf()
    fun getResponse() = _response

    init {
        getAllCoins()
    }

    private fun getAllCoins() {

        viewModelScope.launch {
            val result = publicRepository.getAllCoins()
            val properties = result::class.memberProperties

            for (prop in properties){
                val value = prop.getter.call(result)
                listCoins.add(value as Coins)
            }
            _response.postValue(listCoins)
        }

    }

}
