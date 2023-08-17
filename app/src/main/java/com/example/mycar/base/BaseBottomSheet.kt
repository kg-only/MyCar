package com.example.mycar.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.example.mycar.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseBottomSheet<VB : ViewBinding> : DialogFragment() {

    private var bindingHolder: VB? = null
    protected val binding: VB
        get() = bindingHolder!!

    protected open val isDismissable: Boolean = true

    private var peekHeight = 0
    private var behavior = BottomSheetBehavior.STATE_EXPANDED
    private var sheetTheme = R.style.Theme_MyCar
    override fun getTheme(): Int = sheetTheme


    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextInflater = LayoutInflater.from(requireContext())
        bindingHolder = createSheet(contextInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSheet()
        setupObservers()
        setupBottomSheetBehavior(view)
    }

    abstract fun setupSheet()

    open fun setupObservers() {}

    abstract fun createSheet(inflater: LayoutInflater): VB

    private fun setupBottomSheetBehavior(view: View) {
        if (isDismissable)
            view.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    val dialog = dialog as? BottomSheetDialog? ?: return
                    val dialogBehavior = dialog.behavior
                    dialogBehavior.state = behavior
                    dialogBehavior.peekHeight = peekHeight
                    dialogBehavior.addBottomSheetCallback(object :
                        BottomSheetBehavior.BottomSheetCallback() {
                        override fun onSlide(bottomSheet: View, dY: Float) {
                            // TODO: Make button layout stick to the bottom through translationY property.
                        }

                        override fun onStateChanged(bottomSheet: View, newState: Int) {
                            if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                                dismiss()
                            }
                        }
                    })
                }
            })
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }
}