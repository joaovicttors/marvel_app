package com.joaovicttors.domain.usecases

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.domain.BaseUseCase
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.domain.repositories.CharacterRepository

class GetCharacterList(
    private val characterRepository: CharacterRepository
) : BaseUseCase<Nothing, List<Character>>() {

    override suspend fun invoke(): Response<List<Character>> {
        return characterRepository.getCharacterList()
    }
}