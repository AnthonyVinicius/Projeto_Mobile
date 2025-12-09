package br.edu.ifpe.alvarium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(1500)
            _event.emit(SplashEvent.NavigateToHome)
        }
    }
}

sealed class SplashEvent {
    object NavigateToHome : SplashEvent()
}