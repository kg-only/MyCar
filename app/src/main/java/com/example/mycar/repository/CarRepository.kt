package com.example.mycar.repository

import com.example.mycar.model.CarModel
import com.example.mycar.model.db.CarModelEntity
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    suspend fun insertOneCarToDataBase(car: CarModelEntity)
    suspend fun getCars(): Flow<List<CarModel>>
    suspend fun getCarsByName(name: String): Flow<List<CarModel>>
    suspend fun getCarsByFilter(
        name: String,
        engineCapacity: String,
        date: String
    ): Flow<List<CarModel>>
}