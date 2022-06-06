package com.joaovicttors.domain.usecases

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.domain.BaseUseCase
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.domain.repositories.CharacterRepository

class GetCharacterListUseCase(
    private val characterRepository: CharacterRepository
) : BaseUseCase<Int, List<Character>>() {

    override suspend fun invoke(param: Int): Response<List<Character>> {
        return characterRepository.getCharacterList(param)
    }
}