package com.joaovicttors.presentation.character_list.adapter.vertical

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.presentation.R

@SuppressLint("NotifyDataSetChanged")
class CharacterVListAdapter : RecyclerView.Adapter<CharacterVListItemViewHolder>() {

    private val collection: MutableList<Character> = mutableListOf()

    fun submitList(data: List<Character>) {
        collection.addAll(data).also { notifyDataSetChanged() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVListItemViewHolder {
        return CharacterVListItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.character_vertical_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterVListItemViewHolder, position: Int) {
        holder.bind(collection[position])
    }

    override fun getItemCount(): Int {
        return collection.size
    }
}