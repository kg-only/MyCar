package com.example.mycar.model.dto

import androidx.annotation.DrawableRes

data class CarModelDto(
    val id:Int,
    val name: String,
    @DrawableRes val image: Int,
    val engineCapacity: Double,
    val dateOfCreate: String,
    val imageFromPhone: String? = null
)

