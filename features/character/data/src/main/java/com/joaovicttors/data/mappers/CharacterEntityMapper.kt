package com.joaovicttors.data.mappers

import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.models.CharacterEntity
import com.joaovicttors.domain.entities.Character

class CharacterEntityMapper : BaseMapper<CharacterEntity, Character>() {

    override fun mapToDomainEntity(input: CharacterEntity): Character {
        return Character(
            id = input.characterId,
            name = input.name,
            thumbnail = input.thumbnail,
            description = input.description,
            resourceUri = input.resourceUri,
        )
    }

    override fun mapFromDomainEntity(input: Character): CharacterEntity {
        return CharacterEntity(
            characterId = input.id,
            name = input.name,
            thumbnail = input.thumbnail,
            description = input.description,
            resourceUri = input.resourceUri,
        )
    }
}