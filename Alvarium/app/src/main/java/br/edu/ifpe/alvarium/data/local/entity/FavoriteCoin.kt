package br.edu.ifpe.alvarium.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_coins")
data class FavoriteCoin(
    @PrimaryKey val id: String,
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val currentPrice: Double
)
