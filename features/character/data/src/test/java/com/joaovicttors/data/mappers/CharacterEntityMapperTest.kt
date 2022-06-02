package com.joaovicttors.data.mappers

import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.data.models.CharacterEntity
import com.joaovicttors.domain.entities.Character
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

internal class CharacterEntityMapperTest {

    private lateinit var mapper: BaseMapper<CharacterEntity, Character>

    @Before
    fun before() {
        mapper = CharacterEntityMapper()
    }

    @Test
    fun `when mapToDomainEntity called`() {
        val data = CharacterEntity(id = 1, name = "t", thumbnail = "t", description = "t", resourceUri = "t")
        val expectedData = Character(id = 1, name = "t", thumbnail = "t", description = "t", resourceUri = "t")

        val domainEntity = mapper.mapToDomainEntity(data)

        assertEquals(expectedData, domainEntity)
    }

    @Test
    fun `when mapFromDomainEntity called`() {
        val data = Character(id = 1, name = "t", thumbnail = "t", description = "t", resourceUri = "t")
        val expectedData =CharacterEntity(id = 1, name = "t", thumbnail = "t", description = "t", resourceUri = "t")

        val domainEntity = mapper.mapFromDomainEntity(data)

        assertEquals(expectedData, domainEntity)
    }
}