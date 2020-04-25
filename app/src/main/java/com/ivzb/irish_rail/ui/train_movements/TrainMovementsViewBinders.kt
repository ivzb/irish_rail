package com.ivzb.irish_rail.ui.train_movements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemTrainMovementBinding
import com.ivzb.irish_rail.model.TrainMovement
import com.ivzb.irish_rail.ui.ItemViewBinder

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
