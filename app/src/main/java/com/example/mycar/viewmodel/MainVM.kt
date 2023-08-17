package com.example.mycar.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mycar.base.BaseVM
import com.example.mycar.model.CarModel
import com.example.mycar.repository.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class MainVM(val repo: CarRepository) : BaseVM() {

    private val _carsData = MutableStateFlow<List<CarModel>>(emptyList())
    val carsData: StateFlow<List<CarModel>> = _carsData

    private val _infoCarData = MutableStateFlow<CarModel?>(null)
    val infoCarData: StateFlow<CarModel?> = _infoCarData

    fun setOneCarForInfo(car: CarModel) {
        _infoCarData.value = car
    }

    fun getCars() = viewModelScope.launch {
        viewModelScope.launch {
            try {
//                setIsLoading(true)
                repo.getCars().collect { _carsData.value = it }
            } catch (e: Exception) {
                Log.e("###", "mistake")
            } finally {
//                setIsLoading(false)
            }
        }
    }

    fun searchByText(name: String) {
        viewModelScope.launch {
            try {
//                setIsLoading(true)
                repo.getCarsByName(name).collect { _carsData.value = it }
            } catch (e: Exception) {
                Log.e("###", "mistake")
            } finally {
//                setIsLoading(false)
            }
        }
    }

    fun searchByFilters(name: String, engineCapacity: String, date: String) {
        viewModelScope.launch {
            try {
//                setIsLoading(true)
                repo.getCarsByFilter(name, engineCapacity, date).collect { _carsData.value = it }
            } catch (e: Exception) {
                Log.e("###", "mistake")
            } finally {
//                setIsLoading(false)
            }
        }
    }
}