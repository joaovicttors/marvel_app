package com.joaovicttors.domain.repositories

import com.joaovicttors.bases.Response
import com.joaovicttors.domain.entities.Character

interface CharacterRepository {

    suspend fun getCharacterList(offset: Int): Response<List<Character>>
}