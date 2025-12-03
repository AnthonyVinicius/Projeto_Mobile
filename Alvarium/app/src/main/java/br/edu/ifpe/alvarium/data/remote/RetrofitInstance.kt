package br.edu.ifpe.alvarium.data.remote

import br.edu.ifpe.alvarium.data.remote.api.ICoinGeckoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ICoinGeckoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ICoinGeckoApi::class.java)
    }
}