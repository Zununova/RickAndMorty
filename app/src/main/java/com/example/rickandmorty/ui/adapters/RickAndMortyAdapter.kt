package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.models.rickandmorty.RickAndMortyModel
import com.example.rickandmorty.databinding.OneItemBinding

class RickAndMortyAdapter() :
    ListAdapter<RickAndMortyModel, RickAndMortyAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val binding: OneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(rickAndMortyModel: RickAndMortyModel?) {
            binding.textViewNameHero.text = rickAndMortyModel?.characterModel?.personName ?: "null"
            binding.textViewLastLocation.text =
                rickAndMortyModel?.locationModel?.locationName ?: "null"
            Glide.with(binding.imageViewHero).load(rickAndMortyModel?.characterModel?.image)
                .into(binding.imageViewHero)
            binding.itemCharacterStatus.text = rickAndMortyModel?.characterModel?.status
            binding.itemCharacterSpecies.text = rickAndMortyModel?.characterModel?.species
            binding.textViewFirstSeenIn.text = rickAndMortyModel?.locationModel?.type

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OneItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<RickAndMortyModel>() {

        override fun areItemsTheSame(
            oldItem: RickAndMortyModel,
            newItem: RickAndMortyModel
        ): Boolean {
            return oldItem.characterModel.id == newItem.characterModel.id
        }

        override fun areContentsTheSame(
            oldItem: RickAndMortyModel,
            newItem: RickAndMortyModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}