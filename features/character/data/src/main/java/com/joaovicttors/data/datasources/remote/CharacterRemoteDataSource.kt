package com.joaovicttors.data.datasources.remote

import com.joaovicttors.bases.Response
import com.joaovicttors.domain.entities.Character

interface CharacterRemoteDataSource {

    suspend fun getCharacterList(offset: Int): Response<List<Character>>
}