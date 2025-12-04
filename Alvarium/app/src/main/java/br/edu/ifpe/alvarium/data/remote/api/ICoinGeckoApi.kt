package br.edu.ifpe.alvarium.data.remote.api

import br.edu.ifpe.alvarium.data.remote.dto.CoinDTO
import br.edu.ifpe.alvarium.data.remote.dto.MarketChartResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ICoinGeckoApi {

    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 50,
        @Query("page") page: Int = 1
    ): List<CoinDTO>

    @GET("coins/{id}/market_chart")
    suspend fun getMarketChart(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: Int = 1
    ): MarketChartResponseDTO

}
