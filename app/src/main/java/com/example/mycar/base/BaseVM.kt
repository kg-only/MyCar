package com.example.mycar.base

import androidx.lifecycle.ViewModel
import com.example.mycar.viewmodel.CoreEvent
import com.example.mycar.viewmodel.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseVM : ViewModel() {

    private val _event = MutableStateFlow<CoreEvent>(Event.Loading(false))
    val event: Flow<CoreEvent> = _event.asStateFlow()

    fun setIsLoading(loading: Boolean) {
        _event.value = Event.Loading(loading)
    }
}