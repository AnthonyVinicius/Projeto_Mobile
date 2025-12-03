package br.edu.ifpe.alvarium.data.repository

import br.edu.ifpe.alvarium.data.local.dao.CoinDao
import br.edu.ifpe.alvarium.data.mapper.toDomain
import br.edu.ifpe.alvarium.data.mapper.toEntity
import br.edu.ifpe.alvarium.data.remote.api.ICoinGeckoApi
import br.edu.ifpe.alvarium.domain.model.Coin
import br.edu.ifpe.alvarium.domain.repository.ICoinRepository

class CoinRepositoryImpl(
    private val api: ICoinGeckoApi,
    private val dao: CoinDao
) : ICoinRepository {

    override suspend fun getCoins(): List<Coin> {
        return try {
            val remote = api.getCoins()
            val entities = remote.map { it.toEntity() }
            dao.insertCoins(entities)
            entities.map { it.toDomain() }

        } catch (e: Exception) {
            dao.getAllCoins().map { it.toDomain() }
        }
    }
}