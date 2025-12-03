package br.edu.ifpe.alvarium.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifpe.alvarium.domain.repository.IChartRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: IChartRepository
) : ViewModel() {

    private val _chartData = MutableStateFlow<List<Pair<Long, Double>>>(emptyList())
    val chartData: StateFlow<List<Pair<Long, Double>>> = _chartData

    fun startAutoUpdate(coinId: String) {
        viewModelScope.launch {
            while (true) {
                loadChart(coinId)
                delay(5000)
            }
        }
    }

    private suspend fun loadChart(coinId: String) {
        try {
            val data = repository.getChart(coinId)
            _chartData.value = data
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
