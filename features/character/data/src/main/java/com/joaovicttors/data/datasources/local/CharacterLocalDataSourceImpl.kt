package com.joaovicttors.data.datasources.local

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.datasources.local.service.CharacterLocalService
import com.joaovicttors.data.models.CharacterEntity
import com.joaovicttors.domain.entities.Character

class CharacterLocalDataSourceImpl(
    private val mapper: BaseMapper<CharacterEntity, Character>,
    private val service: CharacterLocalService,
) : CharacterLocalDataSource {

    override suspend fun addCharacterList(characterList: List<Character>) {
        try {
            characterList.map { mapper.mapFromDomainEntity(it) }.let { mappedData ->
                service.addCharacterList(mappedData)
            }
        } catch (error: Exception) {

        }
    }

    override suspend fun getCharacterList(): Response<List<Character>> {
        return try {
            service.getCharacterList().let { data ->
                data.map { mapper.mapToDomainEntity(it) }.let { mappedData ->
                    Response.Success(mappedData)
                }
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }
}