package com.example.mycar.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycar.model.db.CarModelEntity


@Database(entities = [CarModelEntity::class], version = 1,exportSchema = false)
abstract class CarsDatabase : RoomDatabase() {
    abstract fun carsDao(): CarsDao
}