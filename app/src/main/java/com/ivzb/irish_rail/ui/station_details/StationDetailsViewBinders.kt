package com.ivzb.irish_rail.ui.station_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemStationDetailsBinding
import com.ivzb.irish_rail.model.ui.station.StationDetails
import com.ivzb.irish_rail.ui.ItemViewBinder
import com.ivzb.irish_rail.ui.QueryMatcher

class StationDetailsViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val stationDetailsViewModel: StationDetailsViewModel
) : ItemViewBinder<StationDetails, StationDetailsViewHolder>(
    StationDetails::class.java
) {

    override fun createViewHolder(parent: ViewGroup): StationDetailsViewHolder =
        StationDetailsViewHolder(
            ItemStationDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            lifecycleOwner,
            stationDetailsViewModel
        )

    override fun bindViewHolder(model: StationDetails, viewHolder: StationDetailsViewHolder) {
        viewHolder.bind(model)
    }

    override fun getItemType(): Int = R.layout.item_station_details

    override fun areItemsTheSame(oldItem: StationDetails, newItem: StationDetails): Boolean =
        oldItem.stationCode == newItem.stationCode

    override fun areContentsTheSame(oldItem: StationDetails, newItem: StationDetails): Boolean =
        oldItem == newItem
}

class StationDetailsViewHolder(
    private val binding: ItemStationDetailsBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val stationDetailsViewModel: StationDetailsViewModel
) : ViewHolder(binding.root) {

    fun bind(stationDetails: StationDetails) {
        binding.stationDetails = stationDetails
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()

        binding.cvStationDetails.setOnClickListener {
            stationDetailsViewModel.click(stationDetails)
        }
    }
}

class StationDetailsQueryMatcher : QueryMatcher {

    override fun matches(item: Any, query: String): Boolean {
        if (item is StationDetails) {
            listOf(item.trainCode, item.originName, item.stationName, item.destinationName)
                .forEach { if (it.contains(query, ignoreCase = true)) return true }
        }

        return false
    }
}
