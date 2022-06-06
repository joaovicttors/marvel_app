package com.joaovicttors.presentation.character_list.adapter.vertical

import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.presentation.databinding.CharacterVerticalListItemBinding

class CharacterVListItemViewHolder(
    private val binding: CharacterVerticalListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.character = character
    }
}