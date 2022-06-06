package com.joaovicttors.data.repositories

import com.joaovicttors.bases.Response
import com.joaovicttors.data.datasources.local.CharacterLocalDataSource
import com.joaovicttors.data.datasources.remote.CharacterRemoteDataSource
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.domain.repositories.CharacterRepository

class CharacterRepositoryImpl(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {

    override suspend fun getCharacterList(offset: Int): Response<List<Character>> {
        return localDataSource.getCharacterList(offset).let { response ->
            return@let when (response) {
                is Response.Error -> getRemoteCharacterList(offset)
                is Response.Success -> if (response.data.isNotEmpty()) response else getRemoteCharacterList(offset)
            }
        }
    }

    private suspend fun getRemoteCharacterList(offset: Int): Response<List<Character>>  {
        return remoteDataSource.getCharacterList(offset).also { response ->
            if (response is Response.Success) localDataSource.addCharacterList(response.data)
        }
    }
}