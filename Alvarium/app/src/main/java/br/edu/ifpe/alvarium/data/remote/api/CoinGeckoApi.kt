package br.edu.ifpe.alvarium.data.remote.api

import br.edu.ifpe.alvarium.data.remote.dto.CoinDTO
import retrofit2.http.GET

interface CoinGeckoApi {
    @GET("coins/list")
    suspend fun getCoins(): List<CoinDTO>
}