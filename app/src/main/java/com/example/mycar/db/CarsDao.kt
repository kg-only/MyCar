package com.example.mycar.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycar.model.db.CarModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Query("SELECT * FROM cars_table_name")
    fun getAllCars(): Flow<List<CarModelEntity>>

    @Query("SELECT * FROM cars_table_name WHERE name = :name")
    fun getAllCarsByName(name: String): Flow<List<CarModelEntity>>

    @Query("SELECT * FROM cars_table_name WHERE name = :name AND engineCapacity = :engineCapacity AND dateOfCreate = :dateOfCreate")
    fun getAllCarsByFilter(
        name: String,
        engineCapacity: Double,
        dateOfCreate: String
    ): Flow<List<CarModelEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCars(characters: List<CarModelEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneCar(characters: CarModelEntity)

    @Query("SELECT COUNT(*) FROM cars_table_name")
    suspend fun getCarCount(): Int

    @Query("DELETE FROM cars_table_name")
    suspend fun deleteAllAddedCars()
}