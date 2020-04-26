package com.ivzb.irish_rail.ui.stations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemStationBinding
import com.ivzb.irish_rail.model.ui.station.Station
import com.ivzb.irish_rail.ui.ItemViewBinder
import com.ivzb.irish_rail.ui.QueryMatcher

class StationViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val stationsViewModel: StationsViewModel
) : ItemViewBinder<Station, StationViewHolder>(
    Station::class.java
) {

    override fun createViewHolder(parent: ViewGroup): StationViewHolder =
        StationViewHolder(
            ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            lifecycleOwner,
            stationsViewModel
        )

    override fun bindViewHolder(model: Station, viewHolder: StationViewHolder) {
        viewHolder.bind(model)
    }

    override fun getItemType(): Int = R.layout.item_station

    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean =
        oldItem.code == newItem.code

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean =
        oldItem == newItem
}

class StationViewHolder(
    private val binding: ItemStationBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val stationsViewModel: StationsViewModel
) : ViewHolder(binding.root) {

    fun bind(station: Station) {
        binding.station = station
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()

        binding.cvStation.setOnClickListener {
            stationsViewModel.click(station)
        }
    }
}

class StationQueryMatcher : QueryMatcher {

    override fun matches(item: Any, query: String): Boolean {
        if (item is Station) {
            listOf(item.code, item.name)
                .forEach { if (it.contains(query, ignoreCase = true)) return true }

            return false
        }

        return true
    }
}
