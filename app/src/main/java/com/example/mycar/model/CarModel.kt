package com.example.mycar.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarModel(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    val engineCapacity: Double,
    val dateOfCreate: String,
    val imageFromPhone: String? = null
) : Parcelable