package com.joaovicttors.data.datasources.remote.service

import com.joaovicttors.data.models.CharacterResponse

interface CharacterRemoteService {

    suspend fun getCharacterList(): List<CharacterResponse>
}