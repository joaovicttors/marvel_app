package com.joaovicttors.data.mappers

import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.models.CharacterResponse
import com.joaovicttors.domain.entities.Character
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

internal class CharacterResponseMapperTest {

    private lateinit var mapper: BaseMapper<CharacterResponse.Character, Character>

    @Before
    fun before() {
        mapper = CharacterResponseMapper()
    }

    @Test
    fun `when mapToDomainEntity called`() {
        val data = CharacterResponse.Character(
            id = 1,
            name = "t",
            thumbnail = CharacterResponse.Thumbnail(path = "t", extension = "t"),
            description = "t",
            resourceUri = "t"
        )

        val expectedData = Character(
            id = 1,
            name = "t",
            thumbnail = "t/standard_medium.t",
            description = "t",
            resourceUri = "t"
        )

        val domainEntity = mapper.mapToDomainEntity(data)

        TestCase.assertEquals(expectedData, domainEntity)
    }

    @Test(expected = NotImplementedError::class)
    fun `when mapFromDomainEntity called`() {
        val data = Character(
            id = 1,
            name = "t",
            thumbnail = "tt",
            description = "t",
            resourceUri = "t"
        )

        mapper.mapFromDomainEntity(data)
    }
}