package com.joaovicttors.data.datasources.local.service

import com.joaovicttors.data.models.CharacterEntity

interface CharacterLocalService {

    suspend fun addCharacterList(characterList: List<CharacterEntity>)

    suspend fun getCharacterList(): List<CharacterEntity>
}