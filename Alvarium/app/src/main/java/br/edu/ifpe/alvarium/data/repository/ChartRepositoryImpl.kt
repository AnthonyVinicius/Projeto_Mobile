package br.edu.ifpe.alvarium.data.repository

import br.edu.ifpe.alvarium.data.remote.api.ICoinGeckoApi
import br.edu.ifpe.alvarium.data.remote.RetrofitInstance
import br.edu.ifpe.alvarium.domain.repository.IChartRepository

class ChartRepositoryImpl(
    private val api: ICoinGeckoApi = RetrofitInstance.api
) : IChartRepository {

    override suspend fun getChart(coinId: String, days: Int): List<Pair<Long, Double>> {
        val response = api.getMarketChart(
            id = coinId,
            vsCurrency = "usd",
            days = days
        )

        return response.prices.map { it[0].toLong() to it[1] }
    }
}
