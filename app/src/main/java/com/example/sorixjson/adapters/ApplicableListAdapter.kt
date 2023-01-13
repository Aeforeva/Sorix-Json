package com.example.sorixjson.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sorixjson.databinding.ListViewItemBinding
import com.example.sorixjson.model.Applicable

class ApplicableListAdapter (private val clickListener: ApplicableListener) :
    ListAdapter<Applicable, ApplicableListAdapter.ApplicableViewHolder>(DiffCallback) {

    class ApplicableViewHolder(
        private var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: ApplicableListener, applicable: Applicable) {
            binding.applicable = applicable
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Applicable>() {
        override fun areItemsTheSame(oldItem: Applicable, newItem: Applicable): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Applicable, newItem: Applicable): Boolean {
            return oldItem.label == newItem.label
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicableViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApplicableViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ApplicableViewHolder, position: Int) {
        val applicable = getItem(position)
        holder.bind(clickListener, applicable)
    }

}

class ApplicableListener(val clickListener: (applicable: Applicable) -> Unit) {
    fun onClick(applicable: Applicable) = clickListener(applicable)
}