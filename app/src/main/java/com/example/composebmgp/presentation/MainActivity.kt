package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import com.example.data.local.dp.CurrencyDao
import com.example.data.local.dp.CurrencyDatabase
import com.example.data.repo.CurrencyRepositoryImpl
import com.example.domain.CurrencyRepository

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContent {

                    }
                }
            }
        }
         val viewModel: CurrencyViewModel by viewModels()

        viewModel.remoteCurrencies.observe(this) { remoteCurrencies ->
            Log.d("MainActivity", "Remote Currencies: $remoteCurrencies")
        }


        viewModel.cachedCurrencies.apply {
            Log.d("MainActivity", "Cached Currencies: $this")
        }


    }
}

