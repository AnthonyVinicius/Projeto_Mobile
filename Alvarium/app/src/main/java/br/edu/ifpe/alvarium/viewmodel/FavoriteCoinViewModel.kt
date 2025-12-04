package br.edu.ifpe.alvarium.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import br.edu.ifpe.alvarium.data.local.entity.FavoriteCoinEntity
import androidx.lifecycle.viewModelScope
import br.edu.ifpe.alvarium.domain.model.Coin
import br.edu.ifpe.alvarium.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.emptyList


class FavoriteCoinViewModel (
    private val repository: IFavoriteRepository
) : ViewModel() {

    val favorites = repository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun toggleFavorite(coin: Coin) = viewModelScope.launch {
        val isFav = repository.isFavorite(coin.id)

        val entity = FavoriteCoinEntity(
            id = coin.id,
            symbol = coin.symbol,
            name = coin.name,
            imageUrl = coin.image,
            currentPrice = coin.currentPrice
        )

        if (isFav)
            repository.removeFavorite(entity)
        else
            repository.addFavorite(entity)
    }

    suspend fun isFavorite(id: String): Boolean =
        repository.isFavorite(id)

    @Composable
    fun isFavoriteState(id: String): Boolean {
        val favoriteList = favorites.collectAsState().value
        return favoriteList.any { it.id == id }
    }
}