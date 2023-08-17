package com.example.mycar.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.mycar.R
import com.example.mycar.ext.toast
import com.example.mycar.viewmodel.Event
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel : BaseVM, VB : ViewBinding> : Fragment() {

    private lateinit var progressDialog: AlertDialog
    abstract val vm: ViewModel
    lateinit var binding: VB

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //prevent rotation
        binding = getViewBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoader()

        lifecycleScope.launch {
            vm.event.collect { coreEvent ->
                when (coreEvent) {
                    is Event.Loading -> setIsLoading(coreEvent.isLoading)
                    is Event.NotificationError -> requireActivity().toast(coreEvent.throwable.message.toString())
                }
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun setupLoader() {
        progressDialog = AlertDialog.Builder(requireContext()).setView(
            LayoutInflater.from(requireContext()).inflate(R.layout.view_progress_dialog, null)
        ).setCancelable(false).create()

        progressDialog.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) progressDialog.dismiss()
            return@setOnKeyListener true
        }
    }

    open fun setIsLoading(value: Boolean) {
        if (value) showLoading() else hideLoading()
    }

    open fun showLoading() {
        when (requireActivity()) {
            is BaseActivity<*, *> -> (requireActivity() as BaseActivity<*, *>).showLoading()
            else -> progressDialog.show()
        }
    }

    open fun hideLoading() {
        (requireActivity() as? BaseActivity<*, *>)?.hideLoading()
        progressDialog.dismiss()
    }
}

