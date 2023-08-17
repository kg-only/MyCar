package com.example.mycar.di

import androidx.room.Room
import com.example.mycar.constants.Constants.CARS_DATABASE_NAME
import com.example.mycar.db.CarsDatabase
import com.example.mycar.repository.CarRepository
import com.example.mycar.repository.CarRepositoryImpl
import com.example.mycar.viewmodel.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Module {
    val module = module {
        factory<CarRepository> { CarRepositoryImpl(get()) }
        single {
            Room.databaseBuilder(get(), CarsDatabase::class.java, CARS_DATABASE_NAME).build()
        }
        single { get<CarsDatabase>().carsDao() }

        viewModel{MainVM(get())}
    }

}