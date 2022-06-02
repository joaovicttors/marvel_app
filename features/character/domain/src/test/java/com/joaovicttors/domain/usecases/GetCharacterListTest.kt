package com.joaovicttors.domain.usecases

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.domain.BaseUseCase
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.domain.repositories.CharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetCharacterListTest {

    private val characterRepository: CharacterRepository = mockk()

    private lateinit var useCase: BaseUseCase<Nothing, List<Character>>

    @Before
    fun before() {
        useCase = GetCharacterList(characterRepository)
    }

    @Test
    fun `when GetCharacterList called and repository returns Error it should return Error`() = runBlocking { ->
        val expectedErrorMessage = "Runtime Error"

        coEvery { characterRepository.getCharacterList() } returns Response.Error(expectedErrorMessage)

        val response = useCase()

        coVerify(exactly = 1) { characterRepository.getCharacterList() }

        assertTrue(response is Response.Error)
        assertEquals(expectedErrorMessage, (response as Response.Error).message)
    }

    @Test
    fun `when GetCharacterList called and repository returns Success it should return Success`() = runBlocking { ->
        val expectedCharacterList = listOf<Character>(mockk(), mockk())

        coEvery { characterRepository.getCharacterList() } returns Response.Success(expectedCharacterList)

        val response = useCase()

        coVerify(exactly = 1) { characterRepository.getCharacterList() }

        assertTrue(response is Response.Success)
        assertEquals(expectedCharacterList, (response as Response.Success).data)
    }
}