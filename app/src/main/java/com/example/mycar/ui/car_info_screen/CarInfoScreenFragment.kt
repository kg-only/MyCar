package com.example.mycar.ui.car_info_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mycar.base.BaseFragment
import com.example.mycar.databinding.FragmentCarInfoBinding
import com.example.mycar.ext.navigateUp
import com.example.mycar.ext.onBackPressed
import com.example.mycar.viewmodel.MainVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class CarInfoScreenFragment : BaseFragment<MainVM, FragmentCarInfoBinding>() {

    override val vm: MainVM by activityViewModels()
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCarInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed { navigateUp() }
        lifecycleScope.launch {
            vm.infoCarData.collect {
                it?.let { car ->
                        binding.carImage.setImageResource(car.image)
                        binding.nameModel.text = car.name
                        binding.engineCapacity.text = car.engineCapacity.toString()
                        binding.dateOfPublish.text = car.dateOfCreate
                }
            }
        }
    }
}