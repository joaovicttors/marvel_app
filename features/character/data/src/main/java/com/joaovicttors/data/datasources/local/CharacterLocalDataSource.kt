package com.joaovicttors.data.datasources.local

import com.joaovicttors.bases.Response
import com.joaovicttors.domain.entities.Character

interface CharacterLocalDataSource {

    suspend fun addCharacterList(characterList: List<Character>)

    suspend fun getCharacterList(offset: Int): Response<List<Character>>
}