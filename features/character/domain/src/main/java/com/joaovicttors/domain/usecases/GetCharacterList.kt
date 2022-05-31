package com.joaovicttors.domain.usecases

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.domain.BaseUseCase
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterList(
    private val characterRepository: CharacterRepository
) : BaseUseCase<Nothing, List<Character>>() {

    override fun invoke(): Flow<Response<List<Character>>> {
        return characterRepository.characterList()
    }
}