package com.example.mycar.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.mycar.R
import com.example.mycar.ext.toast
import com.example.mycar.viewmodel.Event
import kotlinx.coroutines.launch

abstract class BaseActivity<ViewModel : BaseVM, VB : ViewBinding> : AppCompatActivity() {

    abstract val vm: ViewModel
    lateinit var binding: VB

    private lateinit var progressDialog: AlertDialog
    open fun setIsLoading(value: Boolean) {
        if (value) showLoading() else hideLoading()
    }

    open fun showLoading() {
        progressDialog.show()
    }

    open fun hideLoading() {
        progressDialog.dismiss()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        binding = getViewBinding()
        setContentView(binding.root)
        setupLoader()

        lifecycleScope.launch {
            vm.event.collect { coreEvent ->
                when (coreEvent) {
                    is Event.Loading -> {
                        Log.e("###",coreEvent.isLoading.toString())
                        setIsLoading(coreEvent.isLoading)
                    }
                    is Event.NotificationError -> toast(coreEvent.throwable.message.toString())
                }
            }
        }
    }

    abstract fun getViewBinding(): VB

    @SuppressLint("InflateParams")
    private fun setupLoader() {
        progressDialog = AlertDialog.Builder(this)
            .setView(LayoutInflater.from(this).inflate(R.layout.view_progress_dialog, null))
            .setCancelable(false)
            .create()
        progressDialog.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                progressDialog.dismiss()
                finish()
            }
            return@setOnKeyListener true
        }
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroy() {
        progressDialog.dismiss()
        super.onDestroy()
    }
}