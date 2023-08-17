package com.example.mycar.repository

import com.example.mycar.api.FakeData
import com.example.mycar.converter.Converter.fromDateBase
import com.example.mycar.converter.Converter.toDateBase
import com.example.mycar.db.CarsDao
import com.example.mycar.model.CarModel
import com.example.mycar.model.db.CarModelEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryImpl(private val dao: CarsDao) : CarRepository {

    override suspend fun insertOneCarToDataBase(car: CarModelEntity) {
        dao.insertOneCar(car)
        getCars()
    }

    override suspend fun getCars(): Flow<List<CarModel>> {
        val roomData = dao.getAllCars().map { list -> list.map { it.fromDateBase() } }
        val isRoomEmpty = dao.getCarCount() == 0

        return if (isRoomEmpty) {
            val fakeResponse = FakeData.getFakeResponse()
            dao.insertCars(fakeResponse.map { it.toDateBase() })
            roomData
        } else {
            roomData
        }
    }

    override suspend fun getCarsByName(name: String): Flow<List<CarModel>> {
        return dao.getAllCarsByName(name).map { list -> list.map { it.fromDateBase() } }
    }

    override suspend fun getCarsByFilter(
        name: String,
        engineCapacity: String,
        date: String
    ): Flow<List<CarModel>> {
        return dao.getAllCarsByFilter(name, engineCapacity.toDouble(), date)
            .map { list -> list.map { it.fromDateBase() } }
    }
}