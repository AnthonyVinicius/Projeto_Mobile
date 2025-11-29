package br.edu.ifpe.alvarium.data.remote.dto

data class CoinDTO(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double
)