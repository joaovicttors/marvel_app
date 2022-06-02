package com.joaovicttors.data.datasources.remote

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.datasources.remote.service.CharacterRemoteService
import com.joaovicttors.data.models.CharacterResponse
import com.joaovicttors.domain.entities.Character

class CharacterRemoteDataSourceImpl(
    private val mapper: BaseMapper<CharacterResponse.Character, Character>,
    private val service: CharacterRemoteService,
) : CharacterRemoteDataSource {

    override suspend fun getCharacterList(): Response<List<Character>> {
        return try {
            service.getCharacterList().let { data ->
                data.data.results.map { mapper.mapToDomainEntity(it) }.let { mappedData ->
                    Response.Success(mappedData)
                }
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }
}