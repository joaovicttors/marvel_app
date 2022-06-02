package com.joaovicttors.data.datasources.remote

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.datasources.remote.service.CharacterRemoteService
import com.joaovicttors.data.models.CharacterResponse
import com.joaovicttors.domain.entities.Character
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterRemoteDataSourceImplTest {

    private val mapper: BaseMapper<CharacterResponse.Character, Character> = mockk()
    private val service: CharacterRemoteService = mockk()

    private lateinit var dataSource: CharacterRemoteDataSource

    @Before
    fun before() {
        dataSource = CharacterRemoteDataSourceImpl(mapper, service)
    }

    @Test
    fun `when getCharacterList from service returns Error`() = runBlocking { ->
        val expectedErrorMessage = "Runtime Error"

        coEvery { service.getCharacterList() } throws RuntimeException(expectedErrorMessage)

        val response = dataSource.getCharacterList()

        coVerify(exactly = 0) { mapper.mapToDomainEntity(any()) }
        coVerify(exactly = 0) { mapper.mapFromDomainEntity(any()) }
        coVerify(exactly = 1) { service.getCharacterList() }

        TestCase.assertTrue(response is Response.Error)
        TestCase.assertEquals(expectedErrorMessage, (response as Response.Error).message)
    }

    @Test
    fun `when getCharacterList from service returns Success but mapToDomainEntity returns Error `() = runBlocking { ->
        val data = mockk<CharacterResponse>()
        val data2 = listOf<CharacterResponse.Character>(mockk())
        val expectedErrorMessage = "Runtime Error"

        coEvery { data.data } returns mockk()
        coEvery { data.data.results } returns data2

        coEvery { mapper.mapToDomainEntity(data2[0]) } throws RuntimeException(expectedErrorMessage)
        coEvery { service.getCharacterList() } returns data

        val response = dataSource.getCharacterList()

        coVerify(exactly = 1) { mapper.mapToDomainEntity(any()) }
        coVerify(exactly = 0) { mapper.mapFromDomainEntity(any()) }
        coVerify(exactly = 1) { service.getCharacterList() }

        TestCase.assertTrue(response is Response.Error)
        TestCase.assertEquals(expectedErrorMessage, (response as Response.Error).message)
    }

    @Test
    fun `when getCharacterList and mapToDomainEntity returns Success`() = runBlocking { ->
        val data = mockk<CharacterResponse>()
        val data2 = listOf<CharacterResponse.Character>(mockk())
        val expectedData = listOf<Character>(mockk())

        coEvery { data.data } returns mockk()
        coEvery { data.data.results } returns data2

        coEvery { mapper.mapToDomainEntity(data2[0]) } returns expectedData[0]
        coEvery { service.getCharacterList() } returns data

        val response = dataSource.getCharacterList()

        coVerify(exactly = 1) { mapper.mapToDomainEntity(data2[0]) }
        coVerify(exactly = 0) { mapper.mapFromDomainEntity(any()) }
        coVerify(exactly = 1) { service.getCharacterList() }

        TestCase.assertTrue(response is Response.Success)
        TestCase.assertEquals(expectedData, (response as Response.Success).data)
    }
}