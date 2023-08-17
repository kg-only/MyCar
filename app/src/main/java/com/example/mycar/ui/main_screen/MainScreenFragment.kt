package com.example.mycar.ui.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mycar.R
import com.example.mycar.adapter.CarAdapter
import com.example.mycar.base.BaseFragment
import com.example.mycar.databinding.FragmentMainScreenBinding
import com.example.mycar.ext.navigate
import com.example.mycar.ext.toast
import com.example.mycar.sheet.FilterBottomSheet
import com.example.mycar.viewmodel.MainVM
import kotlinx.coroutines.launch

class MainScreenFragment : BaseFragment<MainVM, FragmentMainScreenBinding>() {

    private val adapter by lazy { CarAdapter() }
    override val vm: MainVM by activityViewModels()
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainScreenBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initVM()
        vm.getCars()
    }

    private fun initVM() {
        lifecycleScope.launch {
            vm.carsData.collect { carsList ->
Log.e("###",carsList.toString())
                adapter.submitList(carsList)
            }
        }
    }

    private fun checkAccount() {

    }

    private fun initUI() {
        with(binding) {
            carRecycler.adapter = adapter

            adapter.setOnClickListener {
                vm.setOneCarForInfo(it)
                navigate(MainScreenFragmentDirections.actionMainScreenFragmentToCarInfoScreenFragment())
            }
            filters.setOnClickListener { filterSheet() }
            search.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!textView.text.isNullOrEmpty()) vm.searchByText(textView.text.toString())
                    else requireActivity().toast(getString(R.string.fill_please))
                }
                false
            }
        }
    }

    private fun filterSheet() {
        val filterSheet = FilterBottomSheet()
        filterSheet.setOnClickListener { name, engineCapacity, dateOfCreate ->
            vm.searchByFilters(name, engineCapacity, dateOfCreate)
        }
        filterSheet.show(childFragmentManager, "filter")
    }
}