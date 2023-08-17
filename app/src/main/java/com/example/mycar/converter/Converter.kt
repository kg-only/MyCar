package com.example.mycar.converter

import com.example.mycar.model.CarModel
import com.example.mycar.model.db.CarModelEntity
import com.example.mycar.model.dto.CarModelDto

object Converter {
    fun CarModelEntity.fromDateBase(): CarModel =
        CarModel(id, name, image, engineCapacity, dateOfCreate)

    fun CarModel.toDateBase(): CarModelEntity =
        CarModelEntity(id, name, image, engineCapacity, dateOfCreate)

    fun CarModelDto.toDateBase(): CarModelEntity =
        CarModelEntity(id, name, image, engineCapacity, dateOfCreate)

    fun CarModelDto.toCarModel(): CarModel = CarModel(id, name, image, engineCapacity, dateOfCreate)
}