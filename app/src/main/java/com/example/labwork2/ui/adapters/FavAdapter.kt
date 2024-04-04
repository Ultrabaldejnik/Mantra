package com.example.labwork2.ui.adapters



import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.labwork2.R
import com.example.labwork2.databinding.ItemHeroBinding
import com.example.labwork2.db.SuperHeroModel

class FavAdapter (private val context: Context):
    ListAdapter<SuperHeroModel,  FavAdapter.FavViewHolder>(HeroFavDiffUtilCallBack()) {

    var onUserClicked: ((SuperHeroModel) -> Unit)? = null
    inner class FavViewHolder(val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
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

open class HeroFavDiffUtilCallBack : DiffUtil.ItemCallback<SuperHeroModel>() {
    override fun areItemsTheSame(oldItem: SuperHeroModel, newItem: SuperHeroModel): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: SuperHeroModel,
        newItem: SuperHeroModel
    ): Boolean = oldItem == newItem
}