package br.edu.ifpe.alvarium.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.ifpe.alvarium.domain.repository.IConverterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ConverterViewModel(
    private val repository: IConverterRepository
) : ViewModel() {

    private val _convertedValue = MutableStateFlow("0,00")
    val convertedValue: StateFlow<String> = _convertedValue

    private val _selectedCoinId = MutableStateFlow("bitcoin")
    val selectedCoinId: StateFlow<String> = _selectedCoinId

    fun selectCoin(id: String) {
        _selectedCoinId.value = id
    }

    fun convert(quantity: Double) {
        viewModelScope.launch {
            val price = repository.getPrice(_selectedCoinId.value, "brl")
            val result = price * quantity
            _convertedValue.value = "R$ %.2f".format(result)
        }
    }
}