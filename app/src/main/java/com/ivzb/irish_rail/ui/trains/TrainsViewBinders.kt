package com.ivzb.irish_rail.ui.trains

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.ItemTrainBinding
import com.ivzb.irish_rail.model.Train
import com.ivzb.irish_rail.ui.ItemViewBinder

class TrainViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val trainsViewModel: TrainsViewModel
) : ItemViewBinder<Train, TrainViewHolder>(
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

    override fun getItemType(): Int = R.layout.item_train

    override fun areItemsTheSame(oldItem: Train, newItem: Train): Boolean =
        oldItem.code == newItem.code

    override fun areContentsTheSame(oldItem: Train, newItem: Train): Boolean =
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
