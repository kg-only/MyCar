package com.example.mycar.api

import com.example.mycar.R
import com.example.mycar.ext.dateOfCreate
import com.example.mycar.model.dto.CarModelDto

object FakeData {

    fun getFakeResponse(): List<CarModelDto> {
        val car1 = CarModelDto(
            1,
            name = "Ford Focus",
            image = R.drawable.ford,
            engineCapacity = 1.6,
            dateOfCreate = dateOfCreate()
        )

        val car2 = CarModelDto(
            2,
            name = "Audi",
            image = R.drawable.audi,
            engineCapacity = 2.6,
            dateOfCreate = dateOfCreate()
        )

        val car3 = CarModelDto(
            3,
            name = "Mercedes",
            image = R.drawable.mercedes,
            engineCapacity = 3.2,
            dateOfCreate = dateOfCreate()
        )

        val car4 = CarModelDto(
            4,
            name = "Toyota",
            image = R.drawable.toyota,
            engineCapacity = 3.0,
            dateOfCreate = dateOfCreate()
        )
        val car5 = CarModelDto(
            5,
            name = "Lamborghini",
            image = R.drawable.lamborghini,
            engineCapacity = 4.4,
            dateOfCreate = dateOfCreate()
        )
        return listOf(car1, car2, car3, car4, car5)
    }
}




