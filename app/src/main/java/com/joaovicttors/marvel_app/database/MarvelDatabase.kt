package com.joaovicttors.marvel_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joaovicttors.data.datasources.local.service.CharacterLocalService
import com.joaovicttors.data.models.CharacterEntity

@Database(version = 4, entities = [CharacterEntity::class])
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterLocalService
}