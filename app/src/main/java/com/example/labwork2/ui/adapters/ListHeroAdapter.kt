package com.example.labwork2.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.labwork2.ui.api.SuperHeroModelApi
import com.example.labwork2.R
import com.example.labwork2.databinding.ItemHeroBinding

class ListHeroAdapter (private val context: Context):
    ListAdapter<SuperHeroModelApi, ListHeroAdapter.NoteViewHolder>(NoteDiffUtilCallBack()) {

    var onUserClicked: ((SuperHeroModelApi) -> Unit)? = null
    inner class NoteViewHolder(val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val hero = getItem(position)

        with(holder.binding) {
            tvName.text = context.getString(R.string.hero_name,hero.name)
            tvCreatedBy.text = context.getString(R.string.createdBy,hero.createdby)
        }
        holder.itemView.setOnClickListener{
            onUserClicked?.invoke(hero)
        }
    }
}

open class NoteDiffUtilCallBack : DiffUtil.ItemCallback<SuperHeroModelApi>() {
    override fun areItemsTheSame(oldItem: SuperHeroModelApi, newItem: SuperHeroModelApi): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: SuperHeroModelApi,
        newItem: SuperHeroModelApi
    ): Boolean = oldItem == newItem
}