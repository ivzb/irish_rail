package com.ivzb.irish_rail.ui.train_positions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemTrainPositionBinding
import com.ivzb.irish_rail.model.ui.train.TrainPosition
import com.ivzb.irish_rail.ui.ItemViewBinder
import com.ivzb.irish_rail.ui.QueryMatcher

class TrainPositionsViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val trainsViewModel: TrainPositionsViewModel
) : ItemViewBinder<TrainPosition, TrainPositionViewHolder>(
    TrainPosition::class.java
) {

    override fun createViewHolder(parent: ViewGroup): TrainPositionViewHolder =
        TrainPositionViewHolder(
            ItemTrainPositionBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            lifecycleOwner,
            trainsViewModel
        )

    override fun bindViewHolder(model: TrainPosition, viewHolder: TrainPositionViewHolder) {
        viewHolder.bind(model)
    }

    override fun getItemType(): Int = R.layout.item_train_position

    override fun areItemsTheSame(oldItem: TrainPosition, newItem: TrainPosition): Boolean =
        oldItem.code == newItem.code

    override fun areContentsTheSame(oldItem: TrainPosition, newItem: TrainPosition): Boolean =
        oldItem == newItem
}

class TrainPositionViewHolder(
    private val binding: ItemTrainPositionBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val trainsViewModel: TrainPositionsViewModel
) : ViewHolder(binding.root) {

    fun bind(train: TrainPosition) {
        binding.train = train
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()

        binding.cvTrain.setOnClickListener {
            trainsViewModel.click(train)
        }
    }
}

class TrainPositionQueryMatcher : QueryMatcher {

    override fun matches(item: Any, query: String): Boolean {
        if (item is TrainPosition) {
            listOf(item.code, item.message)
                .forEach { if (it.contains(query, ignoreCase = true)) return true }
        }

        return false
    }
}