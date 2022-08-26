package com.rsstudio.weather.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rsstudio.weather.R
import com.rsstudio.weather.data.network.model.Hourly
import com.rsstudio.weather.ui.main.weather.WeatherType
import com.rsstudio.weather.util.AppHelper

class HourlyForecastAdapter(
    private var context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Hourly> = mutableListOf()
    private var sortBy: Int = 0

    var logTag = "HourlyForecastAdapter"

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvWeek: TextView = view.findViewById(R.id.tvWeek)
        var tvDate: TextView = view.findViewById(R.id.tvDate)
        var ivWeatherType: ImageView = view.findViewById(R.id.ivWeatherType)

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun onBind(item: Hourly, position: Int) {

            //
            var pp = WeatherType.fromWMO(item.weathercode[position])
            ivWeatherType.setImageResource(pp.iconRes)

            if (position == 0) {
                tvWeek.text = "Today"
                tvDate.text = AppHelper.getPatternOfDate(item.time[position])
                tvWeek.setTextColor(ContextCompat.getColor(context, R.color.yellow))
                tvDate.setTextColor(ContextCompat.getColor(context, R.color.yellow))
            } else {
                tvWeek.text = AppHelper.getWeekDayName(item.time[position])
                tvDate.text = AppHelper.getPatternOfDate(item.time[position])
                tvWeek.setTextColor(ContextCompat.getColor(context, R.color.white2))
                tvDate.setTextColor(ContextCompat.getColor(context, R.color.white2))
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_forecast_item, parent, false)
        return ItemViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = list
        if (holder is DailyForecastAdapter.ItemViewHolder) {
            holder.onBind(item[0], position)
        }
    }

    fun submitList(newList: Hourly) {
        list.clear()
        list.add(newList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        if (list.size != 0) {
            return 24;
        }
        return 0
    }

}