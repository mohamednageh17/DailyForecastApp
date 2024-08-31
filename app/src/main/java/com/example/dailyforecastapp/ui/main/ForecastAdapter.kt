package com.example.dailyforecastapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyforecastapp.databinding.ItemForecastBinding
import com.example.dailyforecastapp.utilis.toCelsius
import com.example.domain.model.ForecastItem

class ForecastAdapter() :
    RecyclerView.Adapter<ForecastAdapter.DataViewHolder>() {

    private val data = ArrayList<ForecastItem?>()

    fun setData(list: List<ForecastItem?>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class DataViewHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForecastItem?) {
            with(binding) {

                item?.let {item->
                    dateTV.text = item.dtTxt
                    tempTV.text = "${item.main?.temp?.toCelsius()} c"
                    weatherTV.text = item.weather.firstOrNull()?.description
                }

            }

        }
    }
}
