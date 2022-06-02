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

    override suspend fun getCharacterList(): Response<List<Character>> {
        return localDataSource.getCharacterList().let { response ->
            return@let when (response) {
                is Response.Error -> getRemoteCharacterList()
                is Response.Success -> if (response.data.isNotEmpty()) response else getRemoteCharacterList()
            }
        }
    }

    private suspend fun getRemoteCharacterList(): Response<List<Character>>  {
        return remoteDataSource.getCharacterList().also { response ->
            if (response is Response.Success) localDataSource.addCharacterList(response.data)
        }
    }
}