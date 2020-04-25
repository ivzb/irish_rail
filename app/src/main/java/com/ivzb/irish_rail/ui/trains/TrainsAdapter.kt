package com.ivzb.irish_rail.ui.trains

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias TrainItemClass = Class<out Any>

typealias TrainItemBinder = TrainItemViewBinder<Any, RecyclerView.ViewHolder>

class TrainsAdapter(
    private val viewBinders: Map<TrainItemClass, TrainItemBinder>
) : ListAdapter<Any, RecyclerView.ViewHolder>(TrainsDiffCallback(viewBinders)) {

    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getTrainItemType() }

    private fun getViewBinder(viewType: Int): TrainItemBinder =
        viewTypeToBinders.getValue(viewType)

    override fun getItemViewType(position: Int): Int =
        viewBinders.getValue(super.getItem(position).javaClass).getTrainItemType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewBinder(viewType).createViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        getViewBinder(getItemViewType(position)).bindViewHolder(getItem(position), holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        getViewBinder(holder.itemViewType).onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        getViewBinder(holder.itemViewType).onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }
}

/** Encapsulates logic to create and bind a ViewHolder for a type of item in the Trains. */
abstract class TrainItemViewBinder<M, in VH : RecyclerView.ViewHolder>(
    val modelClass: Class<out M>
) : DiffUtil.ItemCallback<M>() {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(model: M, viewHolder: VH)
    abstract fun getTrainItemType(): Int

    // Having these as non abstract because not all the viewBinders are required to implement them.
    open fun onViewRecycled(viewHolder: VH) = Unit

    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit
}

internal class TrainsDiffCallback(
    private val viewBinders: Map<TrainItemClass, TrainItemBinder>
) : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }

        return viewBinders[oldItem::class.java]?.areItemsTheSame(oldItem, newItem) ?: false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        // We know the items are the same class because [areItemsTheSame] returned true
        return viewBinders[oldItem::class.java]?.areContentsTheSame(oldItem, newItem) ?: false
    }
}
