package br.edu.ifpe.alvarium.domain.repository

interface IConverterRepository {
    suspend fun getPrice(coinId: String, toCurrency: String = "brl"): Double
}