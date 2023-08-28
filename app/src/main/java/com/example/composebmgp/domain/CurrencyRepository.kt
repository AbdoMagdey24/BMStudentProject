package com.example.domain

import androidx.lifecycle.LiveData
import com.example.data.local.dp.CachedCurrency
interface CurrencyRepository {
    fun getCachedCurrenciesLiveData(): LiveData<List<CachedCurrency>>
    suspend fun insertCurrencies(currencies: List<CachedCurrency>)
}
