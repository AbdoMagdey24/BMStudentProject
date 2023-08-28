package com.example.data.repo

import androidx.lifecycle.LiveData
import com.example.data.local.dp.CachedCurrency
import com.example.data.local.dp.CurrencyDao
import com.example.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow

// data/repository/CurrencyRepositoryImpl.kt
class CurrencyRepositoryImpl(private val currencyDao: CurrencyDao) : CurrencyRepository {
    override fun getCachedCurrenciesLiveData(): LiveData<List<CachedCurrency>> {
        return currencyDao.getCurrenciesLiveData()
    }

    override suspend fun insertCurrencies(currencies: List<CachedCurrency>) {
        currencyDao.insertCurrencies(currencies)
    }
}




