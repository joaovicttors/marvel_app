package com.joaovicttors.domain.repositories

import com.joaovicttors.bases.Response
import com.joaovicttors.domain.entities.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun characterList(): Flow<Response<List<Character>>>
}