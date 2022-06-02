package com.joaovicttors.data.mappers

import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.models.CharacterEntity
import com.joaovicttors.domain.entities.Character

class CharacterEntityMapper : BaseMapper<CharacterEntity, Character>() {

    override fun mapToDomainEntity(input: CharacterEntity): Character {
        TODO("Not yet implemented")
    }

    override fun mapFromDomainEntity(input: Character): CharacterEntity {
        TODO("Not yet implemented")
    }
}