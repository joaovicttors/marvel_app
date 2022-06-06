package com.joaovicttors.presentation.character_list

import com.joaovicttors.bases.presentation.BaseViewState
import com.joaovicttors.domain.entities.Character

data class CharacterListViewState(
    override var isLoading: Boolean = false,
    override var data: List<Character> = emptyList(),
    override var errorMessage: String? = null
) : BaseViewState<List<Character>>()