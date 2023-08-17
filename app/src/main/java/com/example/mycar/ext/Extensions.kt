package com.example.mycar.ext

import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateOfCreate(): String {
    val currentTime = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return dateFormat.format(Date(currentTime)).toString()
}

fun Fragment.navigate(action: NavDirections) {
    try {
        this.findNavController().navigate(action)
    } catch (ex: Exception) {
        Log.e("Fragment", "navigate Exception:", ex)
    }
}

fun Fragment.navigateUp() {
    try {
        this.findNavController().navigateUp()
    } catch (ex: Exception) {
        Log.e("Fragment", "navigate Exception:", ex)
    }
}

inline fun Fragment.onBackPressed(crossinline listener: (backPressed: Boolean) -> Unit) {
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                listener.invoke(true)
            }
        }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}

fun <T : Parcelable> Fragment.getArguments(key: String, clazz: Class<T>): T? {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            this.requireArguments().getParcelable(key, clazz)!!
        else
            this.requireArguments().getParcelable(key)!!
    } catch (e: Exception) {
        null
    }
}

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}