package com.example.mycar

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mycar.base.BaseActivity
import com.example.mycar.databinding.ActivityMainBinding
import com.example.mycar.viewmodel.MainVM
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainVM, ActivityMainBinding>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override val vm: MainVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        navHostFragment.navController
    }
}