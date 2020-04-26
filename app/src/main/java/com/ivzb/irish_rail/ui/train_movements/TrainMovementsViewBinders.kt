package com.ivzb.irish_rail.ui.train_movements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemTrainMovementBinding
import com.ivzb.irish_rail.model.ui.train.TrainMovement
import com.ivzb.irish_rail.ui.ItemViewBinder
import com.ivzb.irish_rail.ui.QueryMatcher

class TrainMovementsViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val trainsViewModel: TrainMovementsViewModel
) : ItemViewBinder<TrainMovement, TrainMovementViewHolder>(
    TrainMovement::class.java
) {

    override fun createViewHolder(parent: ViewGroup): TrainMovementViewHolder =
        TrainMovementViewHolder(
            ItemTrainMovementBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            lifecycleOwner,
            trainsViewModel
        )

    override fun bindViewHolder(model: TrainMovement, viewHolder: TrainMovementViewHolder) {
        viewHolder.bind(model)
    }

    override fun getItemType(): Int = R.layout.item_train_movement

    override fun areItemsTheSame(oldItem: TrainMovement, newItem: TrainMovement): Boolean =
        oldItem.code == newItem.code

    override fun areContentsTheSame(oldItem: TrainMovement, newItem: TrainMovement): Boolean =
        oldItem == newItem
}

class TrainMovementViewHolder(
    private val binding: ItemTrainMovementBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val trainsViewModel: TrainMovementsViewModel
) : ViewHolder(binding.root) {

    fun bind(train: TrainMovement) {
        binding.train = train
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()

        binding.cvTrain.setOnClickListener {
            trainsViewModel.click(train)
        }
    }
}

class TrainMovementQueryMatcher : QueryMatcher {

    override fun matches(item: Any, query: String): Boolean {
        if (item is TrainMovement) {
            listOf(item.code, item.locationName, item.locationCode)
                .forEach { if (it.contains(query, ignoreCase = true)) return true }

            return false
        }

        return true
    }
}
