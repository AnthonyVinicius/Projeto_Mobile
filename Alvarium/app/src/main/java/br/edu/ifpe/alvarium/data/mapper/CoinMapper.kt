package br.edu.ifpe.alvarium.data.mapper

import br.edu.ifpe.alvarium.data.local.entity.CoinEntity
import br.edu.ifpe.alvarium.data.remote.dto.CoinDTO
import br.edu.ifpe.alvarium.domain.model.Coin

fun CoinDTO.toEntity() = CoinEntity(
    id = id,
    symbol = symbol,
    name = name,
    image = image,
    currentPrice = current_price
)

fun CoinEntity.toDomain() = Coin(
    id = id,
    symbol = symbol,
    name = name,
    image = image,
    currentPrice = currentPrice
)
