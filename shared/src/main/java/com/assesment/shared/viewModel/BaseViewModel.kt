package com.assesment.shared.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : UiEvent, S : UiState, F : UiEffect> : ViewModel() {
    private val _initialState: S by lazy { createInitialState() }
    private val _uiState: MutableStateFlow<S> = MutableStateFlow(_initialState)
    private val _uiEvent: MutableSharedFlow<E> = MutableSharedFlow()
    private val _uiEffect: Channel<F> = Channel()

    val currentState: S get() = _initialState
    val uiState: StateFlow<S> get() = _uiState.asStateFlow()
    val uiEvent: SharedFlow<E> get() = _uiEvent.asSharedFlow()
    val uiEffect: Flow<F> get() = _uiEffect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    abstract fun createInitialState(): S

    abstract fun handleEvent(event: E)

    private fun subscribeEvents() {
        viewModelScope.launch {
            uiEvent.collect {
                handleEvent(it)
            }
        }
    }

    fun setEvent(event: E) {
        val newEvent = event
        viewModelScope.launch {
            _uiEvent.emit(newEvent)
        }
    }

    fun setState(reduce: S.() -> S) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    fun setEffect(builder: () -> F) {
        val newEffect = builder()
        viewModelScope.launch {
            _uiEffect.send(newEffect)
        }
    }
}