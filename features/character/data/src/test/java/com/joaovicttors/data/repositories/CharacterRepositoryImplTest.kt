package com.joaovicttors.data.repositories

import com.joaovicttors.bases.Response
import com.joaovicttors.data.datasources.local.CharacterLocalDataSource
import com.joaovicttors.data.datasources.remote.CharacterRemoteDataSource
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

internal class CharacterRepositoryImplTest {
    private val localDataSource: CharacterLocalDataSource = mockk()
    private val remoteDataSource: CharacterRemoteDataSource = mockk()

    private lateinit var repository: CharacterRepository

    @Before
    fun before() {
        repository = CharacterRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `getCharacterList from local and remote datasource returns Error`() = runBlocking { ->
            val expectedErrorMessage = "Runtime Error"

            coEvery { localDataSource.getCharacterList() } returns Response.Error("")
            coEvery { remoteDataSource.getCharacterList() } returns Response.Error(expectedErrorMessage)

            val response = repository.getCharacterList()

            coVerify(exactly = 0) { localDataSource.addCharacterList(any()) }
            coVerify(exactly = 1) { localDataSource.getCharacterList() }
            coVerify(exactly = 1) { remoteDataSource.getCharacterList() }

            assertTrue(response is Response.Error)
            assertEquals(expectedErrorMessage, (response as Response.Error).message)
        }

    @Test
    fun `getCharacterList from local datasource returns Error and remote returns Success`() = runBlocking { ->
            val expectedData = listOf<Character>(mockk(), mockk())

            coEvery { localDataSource.addCharacterList(expectedData) } returns Unit
            coEvery { localDataSource.getCharacterList() } returns Response.Error("")
            coEvery { remoteDataSource.getCharacterList() } returns Response.Success(expectedData)

            val response = repository.getCharacterList()

            coVerify(exactly = 1) { localDataSource.addCharacterList(expectedData) }
            coVerify(exactly = 1) { localDataSource.getCharacterList() }
            coVerify(exactly = 1) { remoteDataSource.getCharacterList() }

            assertTrue(response is Response.Success)
            assertEquals(expectedData, (response as Response.Success).data)
        }

    @Test
    fun `getCharacterList from local datasource returns Success and data is empty`() = runBlocking { ->
        val expectedData = listOf<Character>(mockk(), mockk())

        coEvery { localDataSource.addCharacterList(expectedData) } returns Unit
        coEvery { localDataSource.getCharacterList() } returns Response.Success(emptyList())
        coEvery { remoteDataSource.getCharacterList() } returns Response.Success(expectedData)

        val response = repository.getCharacterList()

        coVerify(exactly = 1) { localDataSource.addCharacterList(expectedData) }
        coVerify(exactly = 1) { localDataSource.getCharacterList() }
        coVerify(exactly = 1) { remoteDataSource.getCharacterList() }

        assertTrue(response is Response.Success)
        assertEquals(expectedData, (response as Response.Success).data)
    }

    @Test
    fun `getCharacterList from local datasource returns Success and data is not empty`() = runBlocking { ->
        val expectedData = listOf<Character>(mockk(), mockk())

        coEvery { localDataSource.getCharacterList() } returns Response.Success(expectedData)


        val response = repository.getCharacterList()

        coVerify(exactly = 0) { localDataSource.addCharacterList(any()) }
        coVerify(exactly = 1) { localDataSource.getCharacterList() }
        coVerify(exactly = 0) { remoteDataSource.getCharacterList() }

        assertTrue(response is Response.Success)
        assertEquals(expectedData, (response as Response.Success).data)
    }

}