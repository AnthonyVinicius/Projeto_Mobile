package br.edu.ifpe.alvarium.data.repository

import br.edu.ifpe.alvarium.data.remote.api.ICoinGeckoApi
import br.edu.ifpe.alvarium.domain.repository.IChartRepository

class ChartRepositoryImpl(
    private val api: ICoinGeckoApi
) : IChartRepository {

    override suspend fun getChart(coinId: String, days: Int): List<Pair<Long, Double>> {
        val response = api.getMarketChart(coinId, days = days)
        val prices = response.prices.map { Pair(it[0].toLong(), it[1].toDouble()) }

        val targetPoints = when (days) {
            1 -> 200
            7 -> 300
            30 -> 400
            else -> 200
        }

        if (prices.size <= targetPoints) return prices

        val step = prices.size / targetPoints
        return prices.filterIndexed { index, _ -> index % step == 0 }
    }
}
