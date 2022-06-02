package com.joaovicttors.data.mappers

import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.models.CharacterEntity
import com.joaovicttors.domain.entities.Character

class CharacterEntityMapper : BaseMapper<CharacterEntity, Character>() {

    override fun mapToDomainEntity(input: CharacterEntity): Character {
        return Character(
            input.id,
            input.name,
            input.thumbnail,
            input.description,
            input.resourceUri,
        )
    }

    override fun mapFromDomainEntity(input: Character): CharacterEntity {
        return CharacterEntity(
            input.id,
            input.name,
            input.thumbnail,
            input.description,
            input.resourceUri,
        )
    }
}