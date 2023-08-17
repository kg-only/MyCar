package com.example.mycar.sheet

import android.view.LayoutInflater
import com.example.mycar.base.BaseBottomSheet
import com.example.mycar.databinding.FilterBottomSheetBinding


class FilterBottomSheet : BaseBottomSheet<FilterBottomSheetBinding>() {

    private var onClickListener: ((
        name: String, engineCapacity: String, dateOfCreate: String
    ) -> Unit)? = null

    fun setOnClickListener(
        listener: (
            name: String, engineCapacity: String, dateOfCreate: String
        ) -> Unit
    ) {
        onClickListener = listener
    }

    override fun setupSheet() = with(binding) {
        filterBtn.setOnClickListener {
            if (engineCapacityFilter.text.isNotEmpty()) {
                onClickListener?.invoke(
                    markFilter.text.toString(),
                    engineCapacityFilter.text.toString(),
                    dateOfPublish.text.toString()
                )
                dismiss()
            }
        }
    }

    override fun createSheet(inflater: LayoutInflater): FilterBottomSheetBinding =
        FilterBottomSheetBinding.inflate(inflater)
}