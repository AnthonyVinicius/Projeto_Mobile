package br.edu.ifpe.alvarium.domain.repository

interface IChartRepository {
    suspend fun getChart(coinId: String, days: Int = 7): List<Pair<Long, Double>>
}