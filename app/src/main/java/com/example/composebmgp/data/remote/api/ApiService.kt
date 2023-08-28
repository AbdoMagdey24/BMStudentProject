package com.example.data.remote.api

import com.example.domain.ConversionRate
import com.example.domain.ConversionResult
import com.example.domain.Currency
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("currencies")
    suspend fun getCurrencies(): List<Currency>

    @GET("currencies/{base}/{target}")
    suspend fun getConversionRate(@Path("base") base: String, @Path("target") target: String): ConversionRate

    @GET("currencies/{base}/{target}/{amount}")
    suspend fun getConversionResult(@Path("base") base: String, @Path("target") target: String, @Path("amount") amount: Double): ConversionResult
}
