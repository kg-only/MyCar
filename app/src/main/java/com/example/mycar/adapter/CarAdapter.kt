package com.example.mycar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycar.databinding.CarItemBinding
import com.example.mycar.model.CarModel

class CarAdapter :
    ListAdapter<CarModel, CarAdapter.CarAdapterVH>(diffCallback) {

    private var onClickListener: ((item: CarModel) -> Unit)? = null
    fun setOnClickListener(listener: (item: CarModel) -> Unit) {
        onClickListener = listener
    }

    inner class CarAdapterVH(private val itemBinding: CarItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: CarModel) = with(itemBinding) {
            carImage.setImageResource(item.image)
            nameModel.text = item.name
            engineCapacity.text = item.engineCapacity.toString()
            itemView.setOnClickListener { onClickListener?.invoke(item) }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CarModel>() {
            override fun areItemsTheSame(
                oldItem: CarModel, newItem: CarModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CarModel, newItem: CarModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapterVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CarItemBinding.inflate(layoutInflater, parent, false)
        return CarAdapterVH(binding)
    }

    override fun onBindViewHolder(holder: CarAdapterVH, position: Int) =
        holder.bind(getItem(position))

}