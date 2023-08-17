package com.example.mycar.model.db

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycar.constants.Constants.CARS_TABLE_NAME

@Entity(tableName = CARS_TABLE_NAME)
data class CarModelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    val engineCapacity: Double,
    val dateOfCreate: String,
    val imageFromPhone: String? = null
)