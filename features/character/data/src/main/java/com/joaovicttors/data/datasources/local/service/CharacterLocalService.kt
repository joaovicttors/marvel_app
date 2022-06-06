package com.joaovicttors.data.datasources.local.service

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.joaovicttors.data.models.CharacterEntity

@Dao
interface CharacterLocalService {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacterList(characterList: List<CharacterEntity>)

    @Query(value = "SELECT * FROM character ORDER BY id ASC LIMIT 20 OFFSET :offset")
    suspend fun getCharacterList(offset: Int): List<CharacterEntity>
}