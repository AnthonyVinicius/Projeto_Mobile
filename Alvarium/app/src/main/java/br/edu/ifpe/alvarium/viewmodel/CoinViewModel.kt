package br.edu.ifpe.alvarium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifpe.alvarium.domain.model.Coin
import br.edu.ifpe.alvarium.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinViewModel(
    private val repository: ICoinRepository
) : ViewModel() {

    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins: StateFlow<List<Coin>> = _coins

    init {
        loadCoins()
    }

    fun loadCoins() {
        viewModelScope.launch {
            _coins.value = repository.getCoins()
        }
    }
}
