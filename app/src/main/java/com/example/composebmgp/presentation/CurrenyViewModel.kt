package com.example.currencyconverter

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.local.dp.CachedCurrency
import com.example.data.local.dp.CurrencyDatabase
import com.example.data.remote.api.ApiService
import com.example.data.remote.api.RetrofitClient
import com.example.data.repo.CurrencyRepositoryImpl
import com.example.domain.CurrencyRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CurrencyViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {

    private val _remoteCurrencies = MutableLiveData<List<CachedCurrency>>()
    val remoteCurrencies: LiveData<List<CachedCurrency>> = _remoteCurrencies

    lateinit var  cachedCurrencies : List<CachedCurrency>
    init {
        viewModelScope.launch {
            fetchRemoteCurrencies()
        }
    }

     suspend fun fetchRemoteCurrencies() {
        try {
            val apiService = RetrofitClient.createService(ApiService::class.java)
            val currencies = apiService.getCurrencies()
            val cachedCurrencies = currencies.map { currency ->
                CachedCurrency(currency.code, currency.name, currency.icon_url)
            }
            _remoteCurrencies.postValue(cachedCurrencies)
            currencyRepository.insertCurrencies(cachedCurrencies)


            Log.d("CurrencyViewModel", "Remote Data: $cachedCurrencies")
        } catch (e: Exception) {
            Log.e("CurrencyViewModel", "Error fetching remote data: ${e.message}")


            fetchCachedCurrencies()
        }
    }

    private fun fetchCachedCurrencies() {
         cachedCurrencies = currencyRepository.getCachedCurrenciesLiveData().value!!
        _remoteCurrencies.postValue(cachedCurrencies)


        Log.d("CurrencyViewModel", "Cached Data: $cachedCurrencies")
    }
}
