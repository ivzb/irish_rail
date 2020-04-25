package com.ivzb.irish_rail.ui.stations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemStationBinding
import com.ivzb.irish_rail.model.Station

class StationViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val stationsViewModel: StationsViewModel
) : StationItemViewBinder<Station, StationViewHolder>(
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

    override fun areContentsTheSame(oldItem: Station, newItem: Station) =
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

// Shown if there are no stations or fetching stations fails
object StationEmpty

class EmptyViewHolder(itemView: View) : ViewHolder(itemView)

class EmptyStationViewBinder : StationItemViewBinder<StationEmpty, EmptyViewHolder>(
    StationEmpty::class.java
) {

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return EmptyViewHolder(
            LayoutInflater.from(parent.context).inflate(getItemType(), parent, false)
        )
    }

    override fun bindViewHolder(model: StationEmpty, viewHolder: EmptyViewHolder) {}

    override fun getItemType() = R.layout.item_station_empty

    override fun areItemsTheSame(oldItem: StationEmpty, newItem: StationEmpty) = true

    override fun areContentsTheSame(oldItem: StationEmpty, newItem: StationEmpty) = true
}
