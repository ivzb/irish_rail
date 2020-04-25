package com.ivzb.irish_rail.ui.trains

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemTrainBinding
import com.ivzb.irish_rail.model.Train

class TrainViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val trainsViewModel: TrainsViewModel
) : TrainItemViewBinder<Train, TrainViewHolder>(
    Train::class.java
) {

    override fun createViewHolder(parent: ViewGroup): TrainViewHolder =
        TrainViewHolder(
            ItemTrainBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            lifecycleOwner,
            trainsViewModel
        )

    override fun bindViewHolder(model: Train, viewHolder: TrainViewHolder) {
        viewHolder.bind(model)
    }

    override fun getTrainItemType(): Int = R.layout.item_train

    override fun areItemsTheSame(oldItem: Train, newItem: Train): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Train, newItem: Train) =
        oldItem == newItem
}

class TrainViewHolder(
    private val binding: ItemTrainBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val trainsViewModel: TrainsViewModel
) : ViewHolder(binding.root) {

    fun bind(train: Train) {
        binding.train = train
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()

        binding.cvTrain.setOnClickListener {
            trainsViewModel.click(train)
        }
    }
}

// Shown if there are no trains or fetching trains fails
object TrainEmpty

class EmptyViewHolder(itemView: View) : ViewHolder(itemView)

class EmptyTrainViewBinder : TrainItemViewBinder<TrainEmpty, EmptyViewHolder>(
    TrainEmpty::class.java
) {

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return EmptyViewHolder(
            LayoutInflater.from(parent.context).inflate(getTrainItemType(), parent, false)
        )
    }

    override fun bindViewHolder(model: TrainEmpty, viewHolder: EmptyViewHolder) {}

    override fun getTrainItemType() = R.layout.item_train_empty

    override fun areItemsTheSame(oldItem: TrainEmpty, newItem: TrainEmpty) = true

    override fun areContentsTheSame(oldItem: TrainEmpty, newItem: TrainEmpty) = true
}
