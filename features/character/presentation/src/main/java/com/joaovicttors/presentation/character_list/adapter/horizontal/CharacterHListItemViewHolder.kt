package com.joaovicttors.presentation.character_list.adapter.horizontal

import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.presentation.databinding.CharacterHorizontalListItemBinding

class CharacterHListItemViewHolder(
    private val binding: CharacterHorizontalListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.character = character
    }
}