package com.ivzb.irish_rail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.R

typealias ItemClass = Class<out Any>

typealias ItemBinder = ItemViewBinder<Any, RecyclerView.ViewHolder>

class ItemAdapter(
    private val viewBinders: Map<ItemClass, ItemBinder>,
    private val queryMatcher: QueryMatcher
) : ListAdapter<Any, RecyclerView.ViewHolder>(ItemDiffCallback(viewBinders)), Filterable {

    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getItemType() }
    private val filter: Filter = ItemFilter()
    private var list: List<Any>? = null

    private fun getViewBinder(viewType: Int): ItemBinder =
        viewTypeToBinders.getValue(viewType)

    override fun getItemViewType(position: Int): Int =
        viewBinders.getValue(super.getItem(position).javaClass).getItemType()

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

    fun setList(list: List<Any>) {
        this.list = list
        submitList(list)
    }

    override fun getFilter(): Filter {
        return filter
    }

    /** Async item filter **/
    private inner class ItemFilter : Filter() {

        override fun performFiltering(sequence: CharSequence?): FilterResults {
            val query = sequence?.toString() ?: ""
            val filteredList = list?.filter { queryMatcher.matches(it, query) }

            return FilterResults().apply {
                values = filteredList
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            submitList(results?.values as? List<Any>)
        }
    }
}

/** Used to filter search query, should be implemented by each type of item. */
interface QueryMatcher {

    fun matches(item: Any, query: String): Boolean
}

/** Encapsulates logic to create and bind a ViewHolder for a type of item. */
abstract class ItemViewBinder<M, in VH : RecyclerView.ViewHolder>(
    val modelClass: Class<out M>
) : DiffUtil.ItemCallback<M>() {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(model: M, viewHolder: VH)
    abstract fun getItemType(): Int

    // Having these as non abstract because not all the viewBinders are required to implement them.
    open fun onViewRecycled(viewHolder: VH) = Unit

    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit
}

internal class ItemDiffCallback(
    private val viewBinders: Map<ItemClass, ItemBinder>
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

// Shown if there are no items or fetching items fails
object Empty

class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class EmptyViewBinder : ItemViewBinder<Empty, EmptyViewHolder>(
    Empty::class.java
) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return EmptyViewHolder(
            LayoutInflater.from(parent.context).inflate(getItemType(), parent, false)
        )
    }

    override fun bindViewHolder(model: Empty, viewHolder: EmptyViewHolder) {}

    override fun getItemType() = R.layout.item_empty

    override fun areItemsTheSame(oldItem: Empty, newItem: Empty) = true

    override fun areContentsTheSame(oldItem: Empty, newItem: Empty) = true
}

// Shown if there is no connection
object NoConnection

class NoConnectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class NoConnectionViewBinder : ItemViewBinder<NoConnection, NoConnectionViewHolder>(
    NoConnection::class.java
) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NoConnectionViewHolder(
            LayoutInflater.from(parent.context).inflate(getItemType(), parent, false)
        )
    }

    override fun bindViewHolder(model: NoConnection, viewHolder: NoConnectionViewHolder) {}

    override fun getItemType() = R.layout.item_no_connection

    override fun areItemsTheSame(oldItem: NoConnection, newItem: NoConnection) = true

    override fun areContentsTheSame(oldItem: NoConnection, newItem: NoConnection) = true
}
