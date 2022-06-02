package com.joaovicttors.data.mappers

import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.models.CharacterResponse
import com.joaovicttors.domain.entities.Character

class CharacterResponseMapper : BaseMapper<CharacterResponse, Character>() {

    override fun mapToDomainEntity(input: CharacterResponse): Character {
        return Character(
            id = input.id,
            name = input.name,
            thumbnail = input.thumbnail.path + input.thumbnail.extension,
            description = input.description,
            resourceUri = input.resourceUri,
        )
    }

    override fun mapFromDomainEntity(input: Character): CharacterResponse {
        TODO("Not yet implemented")
    }
}