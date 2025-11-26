package br.edu.ifpe.alvarium.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.edu.ifpe.alvarium.data.local.entity.FavoriteCoin

@Dao
interface FavoriteCoinDao {
    @Query("SELECT * FROM favorite_coins")
    suspend fun getAllFavorites(): List<FavoriteCoin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(coin: FavoriteCoin)

    @Delete
    suspend fun deleteFavorite(coin: FavoriteCoin)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_coins WHERE id = :coinId)")
    suspend fun isFavorite(coinId: String): Boolean
}