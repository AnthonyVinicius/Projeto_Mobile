package br.edu.ifpe.alvarium.data.repository

import br.edu.ifpe.alvarium.data.remote.api.ICoinGeckoApi
import br.edu.ifpe.alvarium.domain.repository.IConverterRepository

class ConverterRepositoryImpl(
    private val api: ICoinGeckoApi
) : IConverterRepository {

    override suspend fun getPrice(coinId: String, toCurrency: String): Double {
        val result = api.getCoinById(
            vsCurrency = toCurrency,
            ids = coinId
        )
        return result.firstOrNull()?.current_price ?: 0.0
    }
}