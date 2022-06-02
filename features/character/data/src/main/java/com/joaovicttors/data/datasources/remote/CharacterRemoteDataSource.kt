package com.joaovicttors.data.datasources.remote

import com.joaovicttors.bases.Response
import com.joaovicttors.domain.entities.Character

interface CharacterRemoteDataSource {

    suspend fun getCharacterList(): Response<List<Character>>
}