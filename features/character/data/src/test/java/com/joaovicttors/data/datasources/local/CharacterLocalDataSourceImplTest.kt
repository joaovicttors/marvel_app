package com.joaovicttors.data.datasources.local

import com.joaovicttors.bases.Response
import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.datasources.local.service.CharacterLocalService
import com.joaovicttors.data.models.CharacterEntity
import com.joaovicttors.domain.entities.Character
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterLocalDataSourceImplTest {

    private val mapper: BaseMapper<CharacterEntity, Character> = mockk()
    private val service: CharacterLocalService = mockk()

    private lateinit var dataSource: CharacterLocalDataSource

    @Before
    fun before() {
        dataSource = CharacterLocalDataSourceImpl(mapper, service)
    }

    @Test
    fun `when addCharacterList from service returns Error`() = runBlocking { ->
        val data = listOf<Character>(mockk())
        val expectedData = listOf<CharacterEntity>(mockk())
        val expectedErrorMessage = "Runtime Error"

        coEvery { mapper.mapFromDomainEntity(data[0]) } returns expectedData[0]
        coEvery { service.addCharacterList(any()) } throws RuntimeException(expectedErrorMessage)

        dataSource.addCharacterList(data)

        coVerify(exactly = 0) { mapper.mapToDomainEntity(any()) }
        coVerify(exactly = 1) { mapper.mapFromDomainEntity(data[0]) }
        coVerify(exactly = 1) { service.addCharacterList(any()) }
    }

    @Test
    fun `when mapFromDomainEntity from mapper returns Error`() = runBlocking { ->
        val data = listOf<Character>(mockk())
        val expectedErrorMessage = "Runtime Error"

        coEvery { mapper.mapFromDomainEntity(data[0]) } throws RuntimeException(expectedErrorMessage)

        dataSource.addCharacterList(data)

        coVerify(exactly = 0) { mapper.mapToDomainEntity(any()) }
        coVerify(exactly = 1) { mapper.mapFromDomainEntity(data[0]) }
        coVerify(exactly = 0) { service.addCharacterList(any()) }
    }

    @Test
    fun `when mapFromDomainEntity and addCharacterList returns Success`() = runBlocking { ->
        val data = listOf<Character>(mockk())
        val expectedData = listOf<CharacterEntity>(mockk())

        coEvery { mapper.mapFromDomainEntity(data[0]) } returns expectedData[0]
        coEvery { service.addCharacterList(any()) } returns Unit

        dataSource.addCharacterList(data)

        coVerify(exactly = 0) { mapper.mapToDomainEntity(any()) }
        coVerify(exactly = 1) { mapper.mapFromDomainEntity(data[0]) }
        coVerify(exactly = 1) { service.addCharacterList(any()) }
    }

    @Test
    fun `when getCharacterList from service returns Error`() = runBlocking { ->
        val expectedErrorMessage = "Runtime Error"

        coEvery { service.getCharacterList(0) } throws RuntimeException(expectedErrorMessage)

        val response = dataSource.getCharacterList(0)

        coVerify(exactly = 0) { mapper.mapToDomainEntity(any()) }
        coVerify(exactly = 0) { mapper.mapFromDomainEntity(any()) }
        coVerify(exactly = 1) { service.getCharacterList(0) }

        assertTrue(response is Response.Error)
        assertEquals(expectedErrorMessage, (response as Response.Error).message)
    }

    @Test
    fun `when getCharacterList from service returns Success but mapToDomainEntity returns Error `() = runBlocking { ->
        val data = listOf<CharacterEntity>(mockk())
        val expectedErrorMessage = "Runtime Error"

        coEvery { mapper.mapToDomainEntity(data[0]) } throws RuntimeException(expectedErrorMessage)
        coEvery { service.getCharacterList(0) } returns data

        val response = dataSource.getCharacterList(0)

        coVerify(exactly = 1) { mapper.mapToDomainEntity(any()) }
        coVerify(exactly = 0) { mapper.mapFromDomainEntity(any()) }
        coVerify(exactly = 1) { service.getCharacterList(0) }

        assertTrue(response is Response.Error)
        assertEquals(expectedErrorMessage, (response as Response.Error).message)
    }

    @Test
    fun `when getCharacterList and mapToDomainEntity returns Success`() = runBlocking { ->
        val data = listOf<CharacterEntity>(mockk())
        val expectedData = listOf<Character>(mockk())

        coEvery { mapper.mapToDomainEntity(data[0]) } returns expectedData[0]
        coEvery { service.getCharacterList(0) } returns data

        val response = dataSource.getCharacterList(0)

        coVerify(exactly = 1) { mapper.mapToDomainEntity(data[0]) }
        coVerify(exactly = 0) { mapper.mapFromDomainEntity(any()) }
        coVerify(exactly = 1) { service.getCharacterList(0) }

        assertTrue(response is Response.Success)
        assertEquals(expectedData, (response as Response.Success).data)
    }
}