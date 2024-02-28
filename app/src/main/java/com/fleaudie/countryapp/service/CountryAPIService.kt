package com.fleaudie.countryapp.service

import com.fleaudie.countryapp.model.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class CountryAPIService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // RxJava3 için uygun çağrı adaptörünü ekleyin.
        .build()
        .create(CountryAPI::class.java)

    fun getData() : Single<List<Country>>{
        return api.getCountries()
    }
}