package com.example.mycar.viewmodel

sealed class Event: CoreEvent {
    class Loading(val isLoading: Boolean): Event()
    class NotificationError(val throwable: Exception): Event()
}
interface CoreEvent

